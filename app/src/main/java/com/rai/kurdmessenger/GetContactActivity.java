package com.rai.kurdmessenger;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rai.kurdmessenger.InDeCrypter.CodeInDeCrypter;
import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.callbacks.CodesContactInterface;
import com.rai.kurdmessenger.databinding.ActivityGetContactBinding;
import com.rai.kurdmessenger.log.L;
import com.rai.kurdmessenger.recycler.CodeContacModel;
import com.rai.kurdmessenger.recycler.CodeListAdapter;
import com.rai.kurdmessenger.recycler.ContactByCodeAdapter;
import com.rai.kurdmessenger.sms.SMS;
import com.rai.kurdmessenger.task.TaskLoadeCodesContact;

import java.util.ArrayList;

public class GetContactActivity extends AppCompatActivity  implements CodesContactInterface {

    private AppBarConfiguration appBarConfiguration;
    private ActivityGetContactBinding binding;
    private ContactByCodeAdapter codecontactsAdapter;

    private final int PICK_CONTACT=1;
    private String name="",Phone="";
    private String Code="",send="";
    private ProgressDialog prDialog=null;
    private ArrayList<CodeContacModel> CodeContact=new ArrayList<>();
    private LinearLayout close_contac_code, addcontact;
    private TextView code_name;
    private RecyclerView rvcodscotact;
    private CodeInDeCrypter cidc=new CodeInDeCrypter();
    BroadcastReceiver receiverCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGetContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        Bundle b=getIntent().getExtras();
        Code= b.getString("code");
        send=b.getString("send");
        if(send.equals("1"))
        {
            if(MyApplication.getWritableDatabase().getPremissoin().equals("0")){
                EnableRuntimePermission();
            }else{
                try{
                    Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent,PICK_CONTACT);
                }catch (Exception e) {
                    e.printStackTrace();
                    MyApplication.getWritableDatabase().setPremissoin("0");
                    EnableRuntimePermission();
                }
            }

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Code);


        prdialog("Loading...");

        addcontact = (LinearLayout) findViewById(R.id.addcontact);
        close_contac_code = (LinearLayout) findViewById(R.id.close_contac_code);

        rvcodscotact = (RecyclerView) findViewById(R.id.rvcodscotact);
        code_name = (TextView) findViewById(R.id.code_name);
        code_name.setText(Code);

        rvcodscotact.setLayoutManager(new LinearLayoutManager(this));
        codecontactsAdapter = new ContactByCodeAdapter(this);
        rvcodscotact.setAdapter(codecontactsAdapter);


        new TaskLoadeCodesContact(GetContactActivity.this,Code).execute();
        addcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MyApplication.getWritableDatabase().CodeCountactConter(Code)<10) {
                    if(MyApplication.getWritableDatabase().getPremissoin().equals("0")){
                        EnableRuntimePermission();
                    }else{
                        try{
                            Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                            startActivityForResult(intent,PICK_CONTACT);
                        }catch (Exception e) {
                            e.printStackTrace();
                            MyApplication.getWritableDatabase().setPremissoin("0");
                            EnableRuntimePermission();
                        }
                    }

                }else {
                    L.t(GetContactActivity.this,"شما فقط 10 مخاطب برای هر کد میتوانید داشته باشید.");
                }


            }
        });
        close_contac_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        receiverCode = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                prdialog("Loading...");
                new TaskLoadeCodesContact(GetContactActivity.this,Code).execute();
            }
        };
        registerReceiver(receiverCode, new IntentFilter("code"));
//        L.t(MyApplication.getAppContext(), ""+Code);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                send = "0";
                if(MyApplication.getWritableDatabase().getPremissoin().equals("0")){
                    EnableRuntimePermission();
                }else{
                    try{

                        Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        startActivityForResult(intent,PICK_CONTACT);
                    }catch (Exception e) {
                        e.printStackTrace();
                        MyApplication.getWritableDatabase().setPremissoin("0");
                        EnableRuntimePermission();
                    }
                }
            }
        });
    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(GetContactActivity.this, Manifest.permission.READ_CONTACTS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(GetContactActivity.this,Manifest.permission.SEND_SMS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(GetContactActivity.this,Manifest.permission.READ_SMS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(GetContactActivity.this,Manifest.permission.RECEIVE_SMS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(GetContactActivity.this,Manifest.permission.WAKE_LOCK))
        {

            // Toast.makeText(SendMessage.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
//            Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//            startActivityForResult(intent,PICK_CONTACT);

        } else {

            ActivityCompat.requestPermissions(GetContactActivity.this,new String[]{
                    Manifest.permission.READ_CONTACTS,Manifest.permission.SEND_SMS,
                    Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS,Manifest.permission.WAKE_LOCK}, 1);

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverCode);

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

                            // L.t(this,"\n"+ContctNamVar);

                            if (Integer.parseInt(contctCursorVar.getString(contctCursorVar.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                            {
                                Phone = GetPhoneNumber(ContctUidVar);
                                name = ContctNamVar;

                                if(send.equals("1")) {
                                    L.t(GetContactActivity.this, "در حال ارسال کد...");
//                                    L.t(GetContactActivity.this, ""+cidc.CodeInCrypter(Code,Phone)+"\n\n"+cidc.CodeDeCrypter(cidc.CodeInCrypter(Code,Phone))+"\n\n"+
//                                            cidc.CodePhoneDeCrypter(cidc.CodeInCrypter(Code,Phone)));


                                    if (isCountactExist(Code, Phone)) {
                                        new SMS(GetContactActivity.this,cidc.CodeInCrypter(Code,Phone),Phone,name,1);
                                    } else {

                                        if(MyApplication.getWritableDatabase().CodeCountactConter(Code)<10) {

                                            MyApplication.getWritableDatabase().deleteContactByCodeToAddHemToNewCode(Phone);

                                            new SMS(GetContactActivity.this,cidc.CodeInCrypter(Code,Phone),Phone,name,1);
                                            MyApplication.getWritableDatabase().setTableOfCodeContact(0, Code, Phone, name);

                                        }else {
                                            L.t(GetContactActivity.this,"شما فقط 10 مخاطب برای هر کد میتوانید داشته باشید.");
                                        }

                                    }
                                }else {
                                    if (isCountactExist(Code, Phone)) {
                                        L.t(GetContactActivity.this, "شما قبلا این مخاطب را وارد کرده اید .");

                                    } else {
                                        MyApplication.getWritableDatabase().deleteContactByCodeToAddHemToNewCode(Phone);
                                        MyApplication.getWritableDatabase().setTableOfCodeContact(0, Code, Phone, name);
                                    }
                                }
                                prDialog.dismiss();
                                send();

                            }else{

                                AlertDialog.Builder alert = new AlertDialog.Builder( GetContactActivity.this );
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
    public void prdialog(String msg){
        prDialog=new ProgressDialog(this,R.style.MyD);
        prDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        prDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        prDialog.setMessage(msg);
        prDialog.setCancelable(false);
        prDialog.show();

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
//        super.onSupportNavigateUp();
        onBackPressed();
        return true;
    }


    public static void send(){
        Intent intent=new Intent("code");
        intent.setAction("code");
        MyApplication.getAppContext().sendBroadcast(intent);
    }


    @Override
    public void onCodeContactListLoaded(ArrayList<CodeContacModel> codeContacModels) {
        if(!(codeContacModels.isEmpty())) {
            codecontactsAdapter.setContactByCodeList(codeContacModels);
        }
        prDialog.dismiss();
    }

    public boolean isCountactExist(String Code,String Phone) {

        boolean is=false;

        String c0="",c1="";

        Cursor result = MyApplication.getWritableDatabase().getTableOfCodeContac(Code);
        result.move(0);
        while (result.moveToNext()) {
            c0=Phone;
            c1=result.getString(2);
            if(c0.equals(c1))
            {
                is=true;
            }
        }
        result.close();
        return is;
    }
}