package com.rai.kurdmessenger;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rai.kurdmessenger.Compress.Compressor;
import com.rai.kurdmessenger.Compress.DeCompressor;
import com.rai.kurdmessenger.InDeCrypter.CreateInCrypter;
import com.rai.kurdmessenger.InDeCrypter.DeCrypte;
import com.rai.kurdmessenger.InDeCrypter.InCrypte;
import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.databinding.ActivitySendMessageBinding;
import com.rai.kurdmessenger.log.L;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executor;

public class SendMessageActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivitySendMessageBinding binding;

    private ImageView send_btn,addcontact_btn;
    private EditText phonenumber_et,msg_et;
    private TextView txt_contact_name;
    private InCrypte inc;
    private Compressor com;
    private DeCompressor dec;
    private DeCrypte decr;
    private String phone_number = "";
    private String sms = "";
    private String smsTemp = "";
    private String formattedDate;
    private String uName=null;
    private final int PICK_CONTACT=1;
    private int i=0;
    private CreateInCrypter inCodesms=new CreateInCrypter();
    private String MsgId="";
//    private String KM = "DS:";
private String KM = "KM-";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySendMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setSupportActionBar(binding.toolbar);
        send_btn = (ImageView) findViewById(R.id.send_btn);
        addcontact_btn = (ImageView) findViewById(R.id.addcontact_btn);
        phonenumber_et = (EditText) findViewById(R.id.phonenumber_et);
        msg_et = (EditText) findViewById(R.id.msg_et);
        txt_contact_name = (TextView) findViewById(R.id.txt_contact_name);

//    ----------------------------------------------
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sms=msg_et.getText().toString();
                phone_number=phonenumber_et.getText().toString();
                smsTemp="";

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                formattedDate = df.format(c.getTime());

                if(phone_number.length()<11 || phone_number.length()>13)
                {
                    if(phone_number.equals("")||phone_number.equals(" ")||phone_number.isEmpty()) {
                        AlertDialog.Builder alert = new AlertDialog.Builder( SendMessageActivity.this );
                        alert.setCancelable( false );
                        alert.setTitle("خطا");
                        alert.setMessage("لطفا شماره را وارد کنید");
                        alert.setPositiveButton("ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //  System.exit(0);
                                        dialog.dismiss();
                                    }
                                }
                        );
                        alert.create();
                        alert.show();
                    }else{
                        AlertDialog.Builder alert = new AlertDialog.Builder( SendMessageActivity.this );
                        alert.setCancelable( false );
                        alert.setTitle("خطا");
                        alert.setMessage("لطفا شماره را وارد کنید");
                        alert.setPositiveButton("ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //  System.exit(0);
                                        dialog.dismiss();
                                    }
                                }
                        );
                        alert.create();
                        alert.show();
                    }
                }

//                ------------------------------------
                else{

                    if(!(sms.equals("")|| sms.equals(null))) {

                        for (int i = 0; i < sms.length(); i++) {
//                        char c=cms[i];
                            smsTemp = smsTemp + inc.incrypte(sms.charAt(i));

                        }
                        // sendSMS(num,sms);
                        smsTemp = com.compres(smsTemp);
                        smsTemp = InCodeSMSByContactCode(smsTemp,phone_number);
                        smsTemp = KM+"" + smsTemp ;

//                        L.t(MyApplication.getAppContext(), smsTemp + "\n\nsend to : " + num + "       \n" + num.length());




                        if(uName==null) {
                            uName=getName(phone_number);
                            if(uName==null) {
                                MyApplication.getWritableDatabase().setTableOfMsg(0, sms, phone_number, phone_number, formattedDate, "0", "0");
                                MyApplication.getWritableDatabase().setTableOfCon(0, phone_number, phone_number, "0", "0");
                            }else {
                                MyApplication.getWritableDatabase().setTableOfMsg(0, sms, phone_number, uName,formattedDate,"0", "0");
                                MyApplication.getWritableDatabase().setTableOfCon(0,phone_number, uName, "0", "0");
                            }
                        }else{
                            MyApplication.getWritableDatabase().setTableOfMsg(0, sms, phone_number, uName,formattedDate,"0", "0");
                            MyApplication.getWritableDatabase().setTableOfCon(0,phone_number, uName, "0", "0");
                        }
//                        getAllMsgByNum(num);
                        MsgId=getMsgid(sms,formattedDate);
                        try{
                            sendSMS(phone_number, smsTemp);
                        }catch (Exception e) {
                            e.printStackTrace();
                            MyApplication.getWritableDatabase().setPremissoin("0");
                            EnableRuntimePermission();
                            L.t(SendMessageActivity.this,"دوباره تلاش کنید");
                        }

//                        if(i==1) {
//                            L.t(MyApplication.getAppContext(), " id update" + MyApplication.getWritableDatabase().updatemsg(getMsgid(sms, formattedDate), "", "", "", "", "", "1"));
////                            onBackPressed();
//                        }else {
//                            L.t(MyApplication.getAppContext(),""+i);
//                        }
                        // L.t(MyApplication.getAppContext(), " id update"+ getMsgid(sms,formattedDate));

                    }else {
                        AlertDialog.Builder alert = new AlertDialog.Builder( SendMessageActivity.this );
                        alert.setCancelable( false );
                        alert.setMessage("میخواهید پیام خالی بفرستید؟");
                        alert.setPositiveButton(" بله ",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //  System.exit(0);
                                        sms=" ";
                                        if(uName==null || uName.equals("")) {
                                            MyApplication.getWritableDatabase().setTableOfMsg(0, sms, phone_number, phone_number,formattedDate, "0", "0");
                                            MyApplication.getWritableDatabase().setTableOfCon(0,phone_number, phone_number, "0","0");
                                        }else{
                                            MyApplication.getWritableDatabase().setTableOfMsg(0, sms, phone_number, uName,formattedDate,"0", "0");
                                            MyApplication.getWritableDatabase().setTableOfCon(0,phone_number, uName, "0","0");
                                        }
                                        MsgId=getMsgid(sms,formattedDate);
                                        try{
                                            sendSMS(phone_number,KM+""+sms);
                                        }catch (Exception e) {
                                            e.printStackTrace();
                                            MyApplication.getWritableDatabase().setPremissoin("0");
                                            EnableRuntimePermission();
                                            L.t(SendMessageActivity.this,"دوباره تلاش کنید");
                                        }
                                        dialog.dismiss();
                                    }
                                }
                        );
                        alert.setNeutralButton(" خیر ",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //  System.exit(0);
                                        dialog.dismiss();
                                    }
                                }
                        );
                        alert.create();
                        alert.show();
                    }
                }
//                ------------------------------------
            }
        });
//        -----------------------------------------
        addcontact_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MyApplication.getWritableDatabase().getPremissoin().equals("0")) {
                    EnableRuntimePermission();
                } else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        startActivityForResult(intent, PICK_CONTACT);
                    } catch (Exception e) {
                        e.printStackTrace();
                        MyApplication.getWritableDatabase().setPremissoin("0");
                        EnableRuntimePermission();
                    }
                }
            }

        });
//        ---------------------------------------
        phonenumber_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                phonenumber_et.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

//    **********************************************
public void EnableRuntimePermission(){

    if (ActivityCompat.shouldShowRequestPermissionRationale(SendMessageActivity.this, Manifest.permission.READ_CONTACTS)&&
            ActivityCompat.shouldShowRequestPermissionRationale(SendMessageActivity.this,Manifest.permission.SEND_SMS)&&
            ActivityCompat.shouldShowRequestPermissionRationale(SendMessageActivity.this,Manifest.permission.READ_SMS)&&
            ActivityCompat.shouldShowRequestPermissionRationale(SendMessageActivity.this,Manifest.permission.RECEIVE_SMS)&&
            ActivityCompat.shouldShowRequestPermissionRationale(SendMessageActivity.this,Manifest.permission.WAKE_LOCK))
    {
        MyApplication.getWritableDatabase().setPremissoin("1");

        // Toast.makeText(SendMessage.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
        Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent,PICK_CONTACT);

    } else {

        ActivityCompat.requestPermissions(SendMessageActivity.this,new String[]{
                Manifest.permission.READ_CONTACTS,Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS,Manifest.permission.WAKE_LOCK}, 1);

    }
}

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case 1:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {
                    MyApplication.getWritableDatabase().setPremissoin("1");

                    //   Toast.makeText(SendMessage.this,"حالا شما از این برنامه به مخاطب ها دسرسی دارید", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent,PICK_CONTACT);

                } else {

                    // Toast.makeText(SendMessage.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    @SuppressLint("Range")
    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data)
    {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode)
        {

            case (1) :
//                L.T(MyApplication.getAppContext(),resultCode+"  "+PICK_CONTACT);
                if (resultCode == -1)
                {

                    Uri contctDataVar = data.getData();

                    Cursor contctCursorVar = getContentResolver().query(contctDataVar, null,
                            null, null, null);
                    if (contctCursorVar.getCount() > 0)
                    {
                        while (contctCursorVar.moveToNext())
                        {
                            String ContctUidVar = contctCursorVar.getString(contctCursorVar.getColumnIndex(ContactsContract.Contacts._ID));

                            String ContctNamVar = contctCursorVar.getString(contctCursorVar.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            uName=ContctNamVar;
                            // L.t(this,"\n"+ContctNamVar);

                            if (Integer.parseInt(contctCursorVar.getString(contctCursorVar.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                            {
                                // Query phone here. Covered next
                                //String ContctMobVar = contctCursorVar.getString(contctCursorVar.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                // L.t(this,"\n"+ GetPhoneNumber(ContctUidVar));
                                phonenumber_et.setText(GetPhoneNumber(ContctUidVar));
                                txt_contact_name.setText(ContctNamVar);
                                txt_contact_name.setVisibility(View.VISIBLE);

                            }else{
                                AlertDialog.Builder alert = new AlertDialog.Builder( SendMessageActivity.this );
                                alert.setCancelable( false );
                                alert.setTitle("خطا");
                                alert.setMessage("دسترسی به شماره این مخاتب ممکن نیست!!!");
                                alert.setPositiveButton("ok",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //  System.exit(0);
                                                dialog.dismiss();
                                            }
                                        }
                                );
                                alert.create();
                                alert.show();
                            }

                        }
                    }
                }
                break;
        }
    }
    @SuppressLint("Range")
    public String GetPhoneNumber(String id)
    {
        String number = "";
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                null,
                null);

        if(phones.getCount() > 0)
        {
            while(phones.moveToNext())
            {
                number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                number=number.replace(" ","");
                number=number.replace("-","");
            }
            //  L.t(this,"\n"+number);
        }

        phones.close();

        return FilterNumber(number);
    }

    public void prdialog(){
        L.t(MyApplication.getAppContext(),"درحال ارسال...");
    }
    public void sendSMS(String phoneNumber, final String message) {
        prdialog();
        phoneNumber=FilterNumber(phoneNumber);
        String SENT = "s"+MsgId;
        String DELIVERED = "d"+MsgId;


        ArrayList<PendingIntent> SentPenIntents = new ArrayList<>();
        ArrayList<PendingIntent> DelPenIntents = new ArrayList<>();

        PendingIntent sentPI = PendingIntent.getBroadcast(MyApplication.getAppContext(),0,
                new Intent(SENT), PendingIntent.FLAG_IMMUTABLE);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(MyApplication.getAppContext(), 0,
                new Intent(DELIVERED), PendingIntent.FLAG_IMMUTABLE);


        //---when the SMS has been sent---
        MyApplication.getAppContext().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0,final Intent arg1) {
                Executor executor = new Executor() {
                    @Override
                    public void execute(Runnable runnable) {
                        try {
                            String s=""+arg1.getAction();
                            s=s.replace("s","");
                            switch (getResultCode()) {
                                case Activity.RESULT_OK:

//                        Toast.makeText(context, "SMS sent",
//                                Toast.LENGTH_SHORT).show();


                                    MyApplication.getWritableDatabase().updatemsg(s, "", "", "", "", "", "1");
                                    send();
                                    break;
                                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                case SmsManager.RESULT_ERROR_RADIO_OFF:
//                        Toast.makeText(context, "Radio off",
//                        Toast.makeText(context, "Generic failure",
//                                Toast.LENGTH_SHORT).show();
                                    MyApplication.getWritableDatabase().updatemsg(s, "", "", "", "", "", "2");
                                    send();
                                    break;
                                case SmsManager.RESULT_ERROR_NO_SERVICE:
                                    Toast.makeText(MyApplication.getAppContext(), "No service",
                                            Toast.LENGTH_SHORT).show();
                                    MyApplication.getWritableDatabase().updatemsg(s, "", "", "", "", "", "2");
                                    send();
                                    break;
                                case SmsManager.RESULT_ERROR_NULL_PDU:
                                    Toast.makeText(MyApplication.getAppContext(), "Null PDU",
                                            Toast.LENGTH_SHORT).show();
                                    MyApplication.getWritableDatabase().updatemsg(s, "", "", "", "", "", "2");
                                    send();
                                    break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                executor.execute(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        MyApplication.getAppContext().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0,final Intent arg1) {
                Executor executor = new Executor() {
                    @Override
                    public void execute(Runnable runnable) {


                        try {
                            String d=""+arg1.getAction();
                            d=d.replace("d","");
                            switch (getResultCode()) {
                                case Activity.RESULT_OK:
//                        Toast.makeText(context, " SMS delivered",
//                                Toast.LENGTH_SHORT).show();
                                    MyApplication.getWritableDatabase().updatemsg(d, "", "", "", "", "", "3");
                                    send();
                                    break;
                                case Activity.RESULT_CANCELED:
//                        Toast.makeText(context, " SMS not delivered",
//                                Toast.LENGTH_SHORT).show();
                                    MyApplication.getWritableDatabase().updatemsg(d, "", "", "", "", "", "4");
                                    send();
                                    break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                };

                executor.execute(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
        if(message.length()>70) {
            ArrayList<String> smsParts = sms.divideMessage(message);

            for (int i=0; i < smsParts.size(); i++){
                SentPenIntents.add(i, sentPI);
                DelPenIntents.add(i, deliveredPI);
            }
            sms.sendMultipartTextMessage(phoneNumber, null, smsParts, SentPenIntents, DelPenIntents);
        }else if(message.length()<=70){
            sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
        }else {
            sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
        }

        onBackPressed();
    }

    public static void send(){
        Intent intent=new Intent("refsend");
        intent.setAction("refsend");
        MyApplication.getAppContext().sendBroadcast(intent);
    }
    /////////////////////////////////////////
    ///////////////////////////////senmsg/////////////////////////////////////



    public String FilterNumber(String num) {
        num = num.replace("۰", "0");
        num = num.replace("۱", "1");
        num = num.replace("۲", "2");
        num = num.replace("۳", "3");
        num = num.replace("۴", "4");
        num = num.replace("۵", "5");
        num = num.replace("۶", "6");
        num = num.replace("۷", "7");
        num = num.replace("۸", "8");
        num = num.replace("۹", "9");
        num = num.replace("+98", "0");
        return num;
    }
    public String getMsgid(String msg,String time)
    {
        StringBuffer list =new StringBuffer();
        String id="";
        Cursor result=MyApplication.getWritableDatabase().getTableOfMsg();
        result.move(0);
        while(result.moveToNext())
        {
            if(result.getString(1).equals(msg)&&result.getString(4).equals(time)) {
                id=result.getString(0);
            }
        }
        return id;

    }

    public String getName(String Num){
        String Name=null;
        Context context=SendMessageActivity.this;
        Uri personUri = Uri.withAppendedPath( ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Num);

        Cursor cur = context.getContentResolver().query(personUri, new String[] { ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null );

        if( cur.moveToFirst() ) {
            int nameIndex = cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);

            Name = cur.getString(nameIndex);

        }
        return Name;
    }
    public String InCodeSMSByContactCode(String sms,String Phone){
        String Code="";

        Cursor result = MyApplication.getWritableDatabase().getTableOfCodeContacToInDeCrypt(Phone);
        result.move(0);
        while (result.moveToNext()) {
            Code=result.getString(1);
        }
        result.close();
        if(Code.equals("") || Code.equals(null)) {

        }else {
            sms = inCodesms.InCodeSms(sms, Code);
        }
        return sms;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyApplication.getWritableDatabase().updateRmzf("2");

    }
}