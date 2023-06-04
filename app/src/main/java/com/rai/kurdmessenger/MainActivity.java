package com.rai.kurdmessenger;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.callbacks.ChatInterface;
import com.rai.kurdmessenger.databinding.ActivityMainBinding;
import com.rai.kurdmessenger.log.L;
import com.rai.kurdmessenger.recycler.ChatListAdapter;
import com.rai.kurdmessenger.recycler.ChatModel;
import com.rai.kurdmessenger.task.TaskLoadChats;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ChatInterface {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public ProgressDialog prDialog=null;
    ChatListAdapter chatListAdapter;
    RecyclerView rvChatList;
    BroadcastReceiver receiverRefsend,receiverRefget;
    boolean mIsHiding = false;
    private final int ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        receiverRefsend = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                prDialog.dismiss();
                prdialog();
                new TaskLoadChats(MainActivity.this).execute();
            }
        };
        registerReceiver(receiverRefsend, new IntentFilter("refsend"));

        receiverRefget = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                prDialog.dismiss();
                prdialog();
                new TaskLoadChats(MainActivity.this).execute();
            }
        };

        registerReceiver(receiverRefget, new IntentFilter("refget"));


        rvChatList=(RecyclerView) findViewById(R.id.rvChatList);
        rvChatList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        chatListAdapter=new ChatListAdapter(MainActivity.this);
        rvChatList.setAdapter(chatListAdapter);

        prdialog();
//        new TaskLoadChat(MainActivity.this).execute(new Runnable() {
//            @Override
//            public void run() {
//
//            }});
        new TaskLoadChats(MainActivity.this).execute();


        setSupportActionBar(binding.toolbar);
        if(MyApplication.getWritableDatabase().getPremissoin().equals("0")){
            EnableRuntimePermission();
        }


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:"+getPackageName()));
            startActivityForResult(intent,ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
        }

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                L.T(MyApplication.getAppContext(),"sms");
                startActivity(new Intent(MainActivity.this,SendMessageActivity.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        if(MyApplication.getWritableDatabase().getPremissoin().equals("0")){
            EnableRuntimePermission();
        }
       if(MyApplication.getWritableDatabase().getRmz().equals("") || MyApplication.getWritableDatabase().getRmz().equals(null)) {
           MyApplication.getWritableDatabase().setRmz();
       }

        if(MyApplication.getWritableDatabase().getRmz().equals("00")){
//            if(parseNewMsgCount()>0) {
//                ShortcutBadger.applyCount(MyApplication.getAppContext(), parseNewMsgCount());
//            }else {
//                ShortcutBadger.removeCount(MyApplication.getAppContext());
//            }
        }else {
            if(MyApplication.getWritableDatabase().getRmzf().equals("2")) {

                MyApplication.getWritableDatabase().updateRmzf("1");
//

            }else if(MyApplication.getWritableDatabase().getRmzf().equals("1")){

                Intent i = new Intent(MainActivity.this, AppLocker.class);
                i.putExtra("c", "0");
                startActivity(i);

            }
        }
    }

    public void prdialog(){
        prDialog=new ProgressDialog(this,R.style.MyD);
        prDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        prDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        prDialog.setMessage("Loading...");
        prDialog.setCancelable(false);
        prDialog.show();
    }

    @Override
    public void onChatListLoaded(ArrayList<ChatModel> chats) {

        if(!(chats.isEmpty())) {
            chatListAdapter.setConList(chats);
//            rvChatList.scrollToPosition(chats.size()-1);
        }
//        L.T(MyApplication.getAppContext(),"onChatListLoaded");
        prDialog.dismiss();

//        L.t(MainActivity.this,"onList Loded");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            System.exit(0);
//        }
        if(MyApplication.getWritableDatabase().getRmzf().equals("2")) {
            MyApplication.getWritableDatabase().updateRmzf("1");
            exit();
        }else {
            exit();
        }

    }
    public void exit(){

        Intent i=new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        System.exit(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiverRefsend);
        unregisterReceiver(receiverRefget);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.Createcode) {
            startActivity(new Intent(MainActivity.this,CreateCodeActivity.class));
            return true;
        }
        if (id == R.id.codeinc) {
            Intent i = new Intent(MainActivity.this,CodesActivity.class);
            i.putExtra("code","0");
            startActivity(i);
            return true;
        }
        if (id == R.id.lockoff) {
            if(MyApplication.getWritableDatabase().getRmz().equals("00")){
                L.t(MainActivity.this,"قفل غیرفعال است.");
            }else {
                Intent i = new Intent(MainActivity.this, LockActivity.class);
                i.putExtra("c", "2");
                startActivity(i);
            }
            return true;
        }
        if (id == R.id.Lock) {

            if(MyApplication.getWritableDatabase().getRmz().equals("00")){
                Intent i = new Intent(MainActivity.this, LockActivity.class);
                i.putExtra("c", "1");
                startActivity(i);
            }else {
                Intent i=new Intent(MainActivity.this,AppLocker.class);
                i.putExtra("c","3");
                startActivity(i);
            }

            return true;
        }
//        if (id == R.id.delmsg) {
//            if(MyApplication.getWritableDatabase().getDelMsgInPhone().equals("1")){
//                MyApplication.getWritableDatabase().setDelMsgInPhone("0");
//                L.t(MainActivity.this,"Notification Off");
//            }else {
//                MyApplication.getWritableDatabase().setDelMsgInPhone("1");
//                L.t(MainActivity.this,"Notification On");
//            }
//
//            return true;
//        }
        if (id == R.id.aboute) {
            Intent i=new Intent(MainActivity.this,AboutActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_CONTACTS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.SEND_SMS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_SMS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.RECEIVE_SMS)&&
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WAKE_LOCK)&&
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)&&
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)&&
                ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
        )
        {

            // Toast.makeText(SendMessage.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
//            Intent intent=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//            startActivityForResult(intent,PICK_CONTACT);

        } else {

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_CONTACTS,Manifest.permission.SEND_SMS,
                    Manifest.permission.READ_SMS,Manifest.permission.RECEIVE_SMS,Manifest.permission.WAKE_LOCK,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS}, 1);

        }

    }
    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case 1:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    //   Toast.makeText(SendMessage.this,"حالا شما از این برنامه به مخاطب ها دسرسی دارید", Toast.LENGTH_LONG).show();
                    MyApplication.getWritableDatabase().setPremissoin("1");


                } else {

                    // Toast.makeText(SendMessage.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
}