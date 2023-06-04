package com.rai.kurdmessenger;

import android.Manifest;
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
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rai.kurdmessenger.Compress.Compressor;
import com.rai.kurdmessenger.InDeCrypter.InCrypte;
import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.callbacks.MsgInterface;
import com.rai.kurdmessenger.databinding.ActivityChatBinding;
import com.rai.kurdmessenger.log.L;
import com.rai.kurdmessenger.recycler.MsgListAdapter;
import com.rai.kurdmessenger.recycler.MsgModel;
import com.rai.kurdmessenger.sms.SMS;
import com.rai.kurdmessenger.task.TaskLoadMsg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created Created by @rebwar_me.
 */

public class ChatActivity extends AppCompatActivity implements MsgInterface {

    private ActivityChatBinding binding;
    private RecyclerView rv_msg;
    private EditText msg_et;
    private View rootviewc;
    private ImageView send_btn;
    private String sms, smsTemp, formattedDate;
    private MsgListAdapter msgAdapter;
    private String number, name,uid,pic;
    private ArrayList<MsgModel> msgs = new ArrayList<>();
    private InCrypte inc;
    private Compressor com;
    private ImageView chaticon;
    private TextView titlename,titlenum;
    public ProgressDialog prDialog;
    BroadcastReceiver receiverRefsend,receiverRefget;
    private static final String STATE_DATA = "state_datam";
    private LinearLayoutManager layoutManager;

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the movie list to a parcelable prior to rotation or configuration change
        outState.putParcelableArrayList(STATE_DATA, msgs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);


        Bundle b = getIntent().getExtras();
        number = b.getString("num");
        uid = b.getString("id");
        pic = b.getString("pic");
        name = b.getString("name");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);
        msg_et = (EditText) findViewById(R.id.msg_et);
        send_btn = (ImageView) findViewById(R.id.send_btn);
        rv_msg = (RecyclerView) findViewById(R.id.rv_msg);
        titlename = (TextView) findViewById(R.id.titlename);
        titlenum = (TextView) findViewById(R.id.titlenum);
        titlename.setText(name);
        titlenum.setText(number);

        MyApplication.getWritableDatabase().updateTableOfContact(uid,"","",""+0,"");
        layoutManager = new LinearLayoutManager(ChatActivity.this);
        rv_msg.setLayoutManager(layoutManager);
        msgAdapter = new MsgListAdapter(ChatActivity.this);
        rv_msg.setAdapter(msgAdapter);

        receiverRefsend = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                prDialog.dismiss();
                prdialog("Loading...");
                new TaskLoadMsg(ChatActivity.this, number).execute();
            }
        };
        receiverRefget = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                prDialog.dismiss();
                prdialog("Loading...");
                new TaskLoadMsg(ChatActivity.this, number).execute();
            }
        };

        registerReceiver(receiverRefsend, new IntentFilter("refsend"));
        registerReceiver(receiverRefget, new IntentFilter("refget"));
        prdialog("Loading...");
        new TaskLoadMsg(ChatActivity.this, number).execute();


        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sms = msg_et.getText().toString();


                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                formattedDate = df.format(c.getTime());

                if (!(sms.equals("") || sms.equals(" "))) {
                    sendSMS(sms,number,name);
//                        if(i==1) {
//                            L.t(MyApplication.getAppContext(), " id update" + MyApplication.getWritableDatabase().updatemsg(getMsgid(sms, formattedDate), "", "", "", "", "", "1"));
////                            onBackPressed();
//                        }else {
//                            L.t(MyApplication.getAppContext(),""+i);
//                        }
                    // L.t(MyApplication.getAppContext(), " id update"+ getMsgid(sms,formattedDate));

                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(ChatActivity.this);
                    alert.setCancelable(false);
                    alert.setMessage("میخواهید پیام خالی بفرستید؟");
                    alert.setPositiveButton(" بله ",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //  System.exit(0);
                                    sendSMS(" ",number,name);
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
                msg_et.setText("");
            }
        });

    }

    public static void send() {
        Intent intent = new Intent("refsend");
        intent.setAction("refsend");
        MyApplication.getAppContext().sendBroadcast(intent);
    }
    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(ChatActivity.this, Manifest.permission.READ_CONTACTS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(ChatActivity.this,Manifest.permission.SEND_SMS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(ChatActivity.this,Manifest.permission.READ_SMS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(ChatActivity.this,Manifest.permission.RECEIVE_SMS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(ChatActivity.this,Manifest.permission.WAKE_LOCK))
        {

            // Toast.makeText(SendMessage.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
//            Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//            startActivityForResult(intent,PICK_CONTACT);

        } else {

            ActivityCompat.requestPermissions(ChatActivity.this,new String[]{
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

                } else {

                    // Toast.makeText(SendMessage.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

    public void sendSMS(final String sms,final String number,final String name) {
        //////////////
        new SMS(MyApplication.getAppContext(), sms, number, name, 0);


//        final Handler pauseSms = new Handler();
//        Runnable seekSms = new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    try {
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        pauseSms.postDelayed(seekSms,1000);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(MyApplication.getWritableDatabase().getPremissoin().equals("0")){
            EnableRuntimePermission();
        }

    }
    public static int parseNewMsgCount(){
        int cn=0;
        Cursor resultmsg=MyApplication.getWritableDatabase().getTableOfContact();
        resultmsg.move(0);
        while(resultmsg.moveToNext())
        {
            cn+=resultmsg.getInt(3);
        }
        resultmsg.close();

        return cn;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyApplication.getWritableDatabase().updateTableOfContact(uid,"","",""+0,"");

        this.finish();
        send();
        MyApplication.getWritableDatabase().updateRmzf("2");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverRefsend);
        unregisterReceiver(receiverRefget);
        MyApplication.getWritableDatabase().updateTableOfContact(uid,"","",""+0,"");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void prdialog(String msg) {
        prDialog = new ProgressDialog(this, R.style.MyD);
        prDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        prDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        prDialog.setMessage(msg);
        prDialog.setCancelable(false);
        prDialog.show();
    }

    @Override
    public void onMsgListLoaded(ArrayList<MsgModel> msgs) {
        if (!(msgs.isEmpty())) {
            this.msgs = msgs;
            msgAdapter.setMsgList(msgs);
            rv_msg.scrollToPosition(msgs.size() - 1);
//            rv_msg.getLayoutManager().scrollToPosition(msgs.size() - 1);
//            layoutManager.scrollToPositionWithOffset((msgs.size()-1), 0);
        }
        prDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delteallmsg) {
            AlertDialog.Builder alert = new AlertDialog.Builder( ChatActivity.this );
            alert.setCancelable( true );
            alert.setMessage("تمام پیام های این مخاطب شود؟");
            alert.setPositiveButton(" بله ",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if(MyApplication.getWritableDatabase().DeleteAllMsg(name,number)>0){
                                L.t(MyApplication.getAppContext(), "حذف شد");
                                Intent intent= new Intent(ChatActivity.this,ChatActivity.class);
                                intent.putExtra("num",number);
                                intent.putExtra("id",uid);
                                intent.putExtra("name",name);
                                intent.putExtra("pic",pic);
                                startActivity(intent);
                                ChatActivity.this.finish();
                            }else {
                                L.t(MyApplication.getAppContext(), "حذف نشد");
                            }
                            dialog.dismiss();
                        }
                    }
            );

            alert.setNeutralButton(" خیر ",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }
            );
            alert.create();
            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

