package com.rai.kurdmessenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.databinding.ActivityAppLockerBinding;

public class AppLocker extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityAppLockerBinding binding;
    private RadioButton rb0,rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb10,rb11;
    private TextView txtrmzalert,txtcl;
    private String strpass="",strpasst="";
    private String in="",taeed="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAppLockerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setSupportActionBar(binding.toolbar);
        txtrmzalert=(TextView) findViewById(R.id.txtrmzalertlocker);
        txtcl=(TextView) findViewById(R.id.txtcllocker);

        rb0=(RadioButton) findViewById(R.id.rb0locker);
        rb1=(RadioButton) findViewById(R.id.rb1locker);
        rb2=(RadioButton) findViewById(R.id.rb2locker);
        rb3=(RadioButton) findViewById(R.id.rb3locker);
        rb4=(RadioButton) findViewById(R.id.rb4locker);
        rb5=(RadioButton) findViewById(R.id.rb5locker);
        rb6=(RadioButton) findViewById(R.id.rb6locker);
        rb7=(RadioButton) findViewById(R.id.rb7locker);
        rb8=(RadioButton) findViewById(R.id.rb8locker);
        rb9=(RadioButton) findViewById(R.id.rb9locker);
        rb10=(RadioButton) findViewById(R.id.rb10locker);
        rb11=(RadioButton) findViewById(R.id.rb11locker);


        Bundle b=getIntent().getExtras();
        in= b.getString("c");


        txtrmzalert.setText("رمز ورود را وارد کنید .");

        txtcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taeed="0";
                strpass="";
                strpasst="";
                rb0.setActivated(false);
                rb0.setChecked(false);
                rb1.setActivated(false);
                rb1.setChecked(false);
                rb2.setActivated(false);
                rb2.setChecked(false);
                rb3.setActivated(false);
                rb3.setChecked(false);
                rb4.setActivated(false);
                rb4.setChecked(false);
                rb5.setActivated(false);
                rb5.setChecked(false);
                rb6.setActivated(false);
                rb6.setChecked(false);
                rb7.setActivated(false);
                rb7.setChecked(false);
                rb8.setActivated(false);
                rb8.setChecked(false);
                rb9.setActivated(false);
                rb9.setChecked(false);
                rb10.setActivated(false);
                rb10.setChecked(false);
                rb11.setActivated(false);
                rb11.setChecked(false);
                txtrmzalert.setText("رمز ورود را وارد کنید .");
            }
        });
        ///////////////////////////////////
        rb0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb0.isActivated()){
                    rb0.setActivated(false);
                    rb0.setChecked(false);

                    strpass=strpass.replace("a","");


                }else{
                    rb0.setActivated(true);
                    rb0.setChecked(true);

                    strpass=strpass+"a";

                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }

//                L.t(LockActivity.this,MyApplication.getWritableDatabase().getRmz());

            }
        });
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb1.isActivated()){
                    rb1.setActivated(false);
                    rb1.setChecked(false);

                    strpass=strpass.replace("b","");


                }else{
                    rb1.setActivated(true);
                    rb1.setChecked(true);

                    strpass=strpass+"b";
                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb2.isActivated()){
                    rb2.setActivated(false);
                    rb2.setChecked(false);

                    strpass=strpass.replace("c","");

                }else{

                    strpass=strpass+"c";

                    rb2.setActivated(true);
                    rb2.setChecked(true);

                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb3.isActivated()){
                    rb3.setActivated(false);
                    rb3.setChecked(false);

                    strpass=strpass.replace("d","");


                }else{

                    strpass=strpass+"d";

                    rb3.setActivated(true);
                    rb3.setChecked(true);
                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb4.isActivated()){
                    rb4.setActivated(false);
                    rb4.setChecked(false);

                    strpass=strpass.replace("e","");

                }else{

                    strpass=strpass+"e";
                    rb4.setActivated(true);
                    rb4.setChecked(true);
                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }

            }
        });
        rb5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb5.isActivated()){
                    rb5.setActivated(false);
                    rb5.setChecked(false);

                    strpass=strpass.replace("f","");
                }else{

                    strpass=strpass+"f";

                    rb5.setActivated(true);
                    rb5.setChecked(true);
                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }
            }
        });
        rb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb6.isActivated()){
                    rb6.setActivated(false);
                    rb6.setChecked(false);


                    strpass=strpass.replace("g","");

                }else{

                    strpass=strpass+"g";

                    rb6.setActivated(true);
                    rb6.setChecked(true);
                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }
            }
        });
        rb7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb7.isActivated()){
                    rb7.setActivated(false);
                    rb7.setChecked(false);

                    strpass=strpass.replace("h","");


                }else{

                    strpass=strpass+"h";

                    rb7.setActivated(true);
                    rb7.setChecked(true);
                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }

            }
        });
        rb8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb8.isActivated()){
                    rb8.setActivated(false);
                    rb8.setChecked(false);

                    strpass=strpass.replace("i","");

                }else{

                    strpass=strpass+"i";

                    rb8.setActivated(true);
                    rb8.setChecked(true);
                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }
            }
        });
        rb9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb9.isActivated()){
                    rb9.setActivated(false);
                    rb9.setChecked(false);

                    strpass=strpass.replace("j","");

                }else{

                    strpass=strpass+"j";
                    rb9.setActivated(true);
                    rb9.setChecked(true);
                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }
            }
        });
        rb10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb10.isActivated()){
                    rb10.setActivated(false);
                    rb10.setChecked(false);

                    strpass=strpass.replace("k","");

                }else{

                    strpass=strpass+"k";


                    rb10.setActivated(true);
                    rb10.setChecked(true);
                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }
            }
        });//Π ש Σ Θ ψ α ন Щ ღ ყ ვ כ
        rb11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rb11.isActivated()){
                    rb11.setActivated(false);
                    rb11.setChecked(false);

                    strpass=strpass.replace("l","");

                }else{

                    strpass=strpass+"l";


                    rb11.setActivated(true);
                    rb11.setChecked(true);
                }
                if(in.equals("0")||in.equals("2")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)){
                        MyApplication.getWritableDatabase().updateRmzf("2");
                        if(in.equals("2")){
                            MyApplication.getWritableDatabase().updateRmz("00");
                            MyApplication.getWritableDatabase().updateRmzf("0");
                        }
                        AppLocker.this.finish();
                        startActivity(new Intent(AppLocker.this,MainActivity.class));
                    }else{}
                }else if(in.equals("3")){
                    if(MyApplication.getWritableDatabase().getRmz().equals(strpass)) {
                        AppLocker.this.finish();
                        Intent i = new Intent(AppLocker.this, LockActivity.class);
                        i.putExtra("c", "1");
                        startActivity(i);
                    }
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(in.equals("1")||in.equals("2")){
            MyApplication.getWritableDatabase().updateRmzf("2");
            startActivity(new Intent(AppLocker.this,MainActivity.class));
            AppLocker.this.finish();
        }else if(in.equals("3")){
            MyApplication.getWritableDatabase().updateRmzf("2");
            startActivity(new Intent(AppLocker.this,MainActivity.class));
            AppLocker.this.finish();
        }else {
            AppLocker.this.finish();
            exit();
        }
        this.finish();
    }
    public void exit(){
        Intent i=new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

}
