package com.rai.kurdmessenger;

import android.content.Intent;
import android.database.Cursor;
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

import com.rai.kurdmessenger.InDeCrypter.CreateChars;
import com.rai.kurdmessenger.InDeCrypter.CreateDeCrypter;
import com.rai.kurdmessenger.InDeCrypter.CreateInCrypter;
import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.databinding.ActivityCreateCodeBinding;
import com.rai.kurdmessenger.log.L;

import java.util.Random;

/**
 * Created Created by @rebwar_me.
 */


public class CreateCodeActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCreateCodeBinding binding;

    private RadioButton rb0,rb1,rb2,rb3,rb4,rb5,rb6,rb7,rb8,rb9,rb10,rb11;
    private TextView crtxtrmsshode,txtcl,txtcode;
    private String chx="";
    private String strCode="";
    private CreateChars chs=new CreateChars();
    private CreateInCrypter cic=new CreateInCrypter();
    private CreateDeCrypter cdc=new CreateDeCrypter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreateCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        txtcl=(TextView) findViewById(R.id.txtcl);
        txtcode=(TextView) findViewById(R.id.txtcode);

        getSupportActionBar().setTitle("ساخت کد ");
        rb0=(RadioButton) findViewById(R.id.rb0);
        rb1=(RadioButton) findViewById(R.id.rb1);
        rb2=(RadioButton) findViewById(R.id.rb2);
        rb3=(RadioButton) findViewById(R.id.rb3);
        rb4=(RadioButton) findViewById(R.id.rb4);
        rb5=(RadioButton) findViewById(R.id.rb5);
        rb6=(RadioButton) findViewById(R.id.rb6);
        rb7=(RadioButton) findViewById(R.id.rb7);
        rb8=(RadioButton) findViewById(R.id.rb8);
        rb9=(RadioButton) findViewById(R.id.rb9);
        rb10=(RadioButton) findViewById(R.id.rb10);
        rb11=(RadioButton) findViewById(R.id.rb11);

        txtcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                strCode="";

            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(strCode.length()<6){
                    L.t(CreateCodeActivity.this,"تعداد نقاط الگو نباید از 6 کمتر باشد.");
                }else {

                    String sms="bcdEFGH سلام خوبی @#123{}";
                    String c=CreateCode(strCode);

                    //     L.t(CreateCode.this,""+"\nCode Created : "+c+"\n SMS : "+sms+"\nmain Code : "+strCode+"\n\nInCodeSms : "+cic.InCodeSms(sms,c)+"\n\nDeCodeSms : "+cdc.DeCodeSms(cic.InCodeSms(sms,c),c)+"\n\n");



//                    txtcode.setText("\n\n\n\n"+c+"\n"+sms+"\n"+strCode+"\n\n"+cic.InCodeSms(sms,c)+"\n\n"+cdc.DeCodeSms(cic.InCodeSms(sms,c),c));
//                    txtcode.setText("");
//                    txtcode.setTextSize(10);
//                    txtcode.setText(c+"\n"+sms+"\n"+cic.InCodeSms(sms,c));

//                    String c=CreateCode(strCode);
//                    txtcode.setText("");
//                   txtcode.setText(c);


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

                    if(isCodeExist(c))
                    {
                        L.t(CreateCodeActivity.this,"شما قبلا این کد را ساخته اید .");
                        strCode="";
                    }else {
                        if(MyApplication.getWritableDatabase().CodeConter()<10) {

                            Intent i = new Intent(CreateCodeActivity.this, CodesActivity.class);
                            i.putExtra("code", c);
                            startActivity(i);
                            CreateCodeActivity.this.finish();
                            L.t(CreateCodeActivity.this,"الگوی ترجمه و کد کردن متن ذخیره شد.");
                        }else {
                            L.t(CreateCodeActivity.this,"شما فقط 10 کد میتوانید داشته باشید.");
                        }
                        strCode="";
                    }

                }

            }

        });


    rb0.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(rb0.isActivated()){
                rb0.setActivated(false);
                rb0.setChecked(false);
                strCode=strCode.replace("Π","");
            }else{
                rb0.setActivated(true);
                rb0.setChecked(true);
                strCode=strCode+"Π";
            }
        }
    });
        rb1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rb1.isActivated()){
                rb1.setActivated(false);
                rb1.setChecked(false);
                strCode=strCode.replace("ש","");

            }else{
                rb1.setActivated(true);
                rb1.setChecked(true);
                strCode=strCode+"ש";
            }

        }
    });

        rb2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rb2.isActivated()){
                rb2.setActivated(false);
                rb2.setChecked(false);
                strCode=strCode.replace("Σ","");
            }else{
                rb2.setActivated(true);
                rb2.setChecked(true);
                strCode=strCode+"Σ";

            }

        }
    });
        rb3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rb3.isActivated()){
                rb3.setActivated(false);
                rb3.setChecked(false);
                strCode=strCode.replace("Θ","");

            }else{
                rb3.setActivated(true);
                rb3.setChecked(true);
                strCode=strCode+"Θ";
            }

        }
    });
        rb4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rb4.isActivated()){
                rb4.setActivated(false);
                rb4.setChecked(false);
                strCode=strCode.replace("ψ","");
            }else{

                rb4.setActivated(true);
                rb4.setChecked(true);
                strCode=strCode+"ψ";
            }
        }
    });
        rb5.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rb5.isActivated()){
                rb5.setActivated(false);
                rb5.setChecked(false);
                strCode=strCode.replace("α","");

            }else{

                rb5.setActivated(true);
                rb5.setChecked(true);
                strCode=strCode+"α";
            }

        }
    });
        rb6.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rb6.isActivated()){
                rb6.setActivated(false);
                rb6.setChecked(false);
                strCode=strCode.replace("ন","");

            }else{

                rb6.setActivated(true);
                rb6.setChecked(true);
                strCode=strCode+"ন";
            }
        }
    });
        rb7.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rb7.isActivated()){
                rb7.setActivated(false);
                rb7.setChecked(false);
                strCode=strCode.replace("Щ","");
            }else{

                rb7.setActivated(true);
                rb7.setChecked(true);
                strCode=strCode+"Щ";
            }

        }
    });
        rb8.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rb8.isActivated()){
                rb8.setActivated(false);
                rb8.setChecked(false);
                strCode=strCode.replace("ღ","");
            }else {

                rb8.setActivated(true);
                rb8.setChecked(true);
                strCode=strCode+"ღ";
            }
        }
    });
        rb9.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rb9.isActivated()){
                rb9.setActivated(false);
                rb9.setChecked(false);
                strCode=strCode.replace("ყ","");

            }else{
                rb9.setActivated(true);
                rb9.setChecked(true);
                strCode=strCode+"ყ";
            }
        }
    });
        rb10.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rb10.isActivated()){
                rb10.setActivated(false);
                rb10.setChecked(false);
                strCode=strCode.replace("ვ","");
            }else{
                rb10.setActivated(true);
                rb10.setChecked(true);
                strCode=strCode+"ვ";
            }
        }
    });
        rb11.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(rb11.isActivated()){
                rb11.setActivated(false);
                rb11.setChecked(false);
                strCode=strCode.replace("כ","");

            }else{
                rb11.setActivated(true);
                rb11.setChecked(true);
                strCode=strCode+"כ";
            }
        }
    });

}



    private String CreateCode(String code){

        code=ch0(code);
        int cx=16-code.length();

        for(int i=0;i<cx;i++) {
            Random r = new Random();
            int x = r.nextInt(20 - 0 + 1) + 0;
            r = new Random();
            int y = r.nextInt((code.length()-1) - 0 + 1) + 0;
            code=code(x,y,code);
        }
        return code;
    }
    private String code(int x,int y,String code)
    {
        String c="";
        for(int i=0;i<code.length();i++)
        {
            if(i==y) {
                c = c + ch(x)+code.charAt(i);
            }else{
                c = c + code.charAt(i);
            }
        }
        return c;
    }
    private String ch(int i){

        String ch="1Й2ফ3প4ე5ნ6ъ8907bcdef";
        ch=ch.charAt(i)+"";
        return ch;
    }
    //Π ש Σ Θ ψ α ন Щ ღ ყ ვ כ
//к ζ წ υ χ ფ д ু ξ ν γ ל
//D B C F E T O N R Q Y X
    private String ch0(String Code){
        String c="";
        for(int i=0;i<Code.length();i++) {
            Random r = new Random();
            int x = r.nextInt(2 - 0 + 1) + 0;
            if(Code.charAt(i)=='Π' || Code.charAt(i)=='к' || Code.charAt(i)=='D') {
                if(x==0){
                    c=c+"Π";
                }else if(x==1){
                    c=c+"к";
                }else {
                    c=c+"D";
                }

            }else  if(Code.charAt(i)=='ש' || Code.charAt(i)=='ζ' || Code.charAt(i)=='B'){
                if(x==0){
                    c=c+"ש";
                }else if(x==1) {
                    c=c+"ζ";
                }else {
                    c=c+"B";
                }
            }else  if(Code.charAt(i)=='Σ' || Code.charAt(i)=='წ' || Code.charAt(i)=='C'){
                if(x==0){
                    c=c+"Σ";
                }else if(x==1) {
                    c=c+"წ";
                }else {
                    c=c+"C";
                }
            }else  if(Code.charAt(i)=='Θ' || Code.charAt(i)=='υ' || Code.charAt(i)=='F'){
                if(x==0){
                    c=c+"Θ";
                }else if(x==1) {
                    c=c+"F";
                }
            }else  if(Code.charAt(i)=='ψ' || Code.charAt(i)=='χ' || Code.charAt(i)=='E'){
                if(x==0){
                    c=c+"ψ";
                }else if(x==1) {
                    c=c+"χ";
                }else {
                    c=c+"E";
                }
            }else  if(Code.charAt(i)=='α' || Code.charAt(i)=='ფ' || Code.charAt(i)=='T'){
                if(x==0){
                    c=c+"α";
                }else if(x==1) {
                    c=c+"ფ";
                }else {
                    c=c+"T";
                }
            }/////
            else  if(Code.charAt(i)=='ন' || Code.charAt(i)=='д' || Code.charAt(i)=='O'){
                if(x==0){
                    c=c+"ন";
                }else if(x==1) {
                    c=c+"д";
                }else {
                    c=c+"O";
                }
            } else  if(Code.charAt(i)=='Щ' || Code.charAt(i)=='ু' || Code.charAt(i)=='N'){
                if(x==0){
                    c=c+"Щ";
                }else if(x==1) {
                    c=c+"ু";
                }else {
                    c=c+"N";
                }
            }else  if(Code.charAt(i)=='ღ' || Code.charAt(i)=='ξ' || Code.charAt(i)=='R'){
                if(x==0){
                    c=c+"ღ";
                }else if(x==1) {
                    c=c+"ξ";
                }else {
                    c=c+"R";
                }
            }else  if(Code.charAt(i)=='ყ' || Code.charAt(i)=='υ' || Code.charAt(i)=='Q'){
                if(x==0){
                    c=c+"ყ";
                }else if(x==1) {
                    c=c+"υ";
                }else {
                    c=c+"Q";
                }
            }else  if(Code.charAt(i)=='ვ' || Code.charAt(i)=='γ' || Code.charAt(i)=='Y'){
                if(x==0){
                    c=c+"ვ";
                }else if(x==1) {
                    c=c+"γ";
                }else {
                    c=c+"Y";
                }
            }else  if(Code.charAt(i)=='כ' || Code.charAt(i)=='ל' || Code.charAt(i)=='X'){
                if(x==0){
                    c=c+"כ";
                }else if(x==1) {
                    c=c+"ל";
                }else {
                    c=c+"X";
                }
            } else {

            }
        }
        return c;
    }

    public boolean isCodeExist(String Code) {

        boolean is=false;

        String c0="",c1="";

        Cursor result = MyApplication.getWritableDatabase().getTableOfCode();
        result.move(0);
        while (result.moveToNext()) {
            c0=chs.anlizCode(Code);
            c1=chs.anlizCode(result.getString(1));
            if(c0.equals(c1))
            {
                is=true;
            }
        }
        result.close();
        return is;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyApplication.getWritableDatabase().updateRmzf("2");
        CreateCodeActivity.this.finish();

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}