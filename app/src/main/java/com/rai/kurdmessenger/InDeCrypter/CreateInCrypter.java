package com.rai.kurdmessenger.InDeCrypter;

/**
 * Created Created by @rebwar_me.
 */

public class CreateInCrypter {

    public static final String CHAR_rus = "ч,Й,И,г,Г,й,Ж,ж,Д,д,и,Ь,ь,Я,К,ю,п,Ъ,э,Э,П,Ф,Щ,Ц,З,з,Т,Ч,к,Б,Ю,Ш,ш,ъ,ф,ц,я,щ,т";
    public static final String CHAR_bangali = "ধ,ফ,শ,ণ,দ,ঐ,র,ত,জ,ঝ,ং,ঁ,ব,ু,ো,ও,ম,্,অ,আ,ই,উ,ঘ,খ,ল,ঔ,ী,ে,হ,ঙ,ৈ,ব,ভ,ক,স,ৌ,ঈ,ূ,ঊ,ন,গ,থ,এ,া,ি,প";
    public static final String CHAR_Georgian = "ი,ა,პ,ძ,ს,ფ,ყ,ვ,უ,თ,შ,ლ,წ,ზ,ღ,კ,ე,გ,ჯ,ჩ,ტ,მ,რ,ო,ნ";
    public static final String CHAR_Hebrew = "ח,ל,ך,צ,מ,ם,פ,ר,ד,א,ו,ה,ט,ז,ש,נ,ב,ג,ק,כ,ע,ס,י,ן";
    public static final String CHAR_Greek = "α,β,Ψ,ψ,δ,Δ,ε,Φ,φ,Γ,γ,η,ι,Ξ,ξ,κ,Λ,λ,μ,ν,Π,π,Ρ,ρ,Σ,σ,τ,Θ,θ,Ω,ω,ς,χ,υ,ζ";
    public static final String CHAR_E = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
    public static final String CHAR_F = "ی,ا,ه,آ,و,ب,ن,س,م,ث,گ,چ,ک,ح,ق,خ,ف,د,غ,ذ,ع,ظ,ز,ط,ژ,ض,ص,ش,ل,ر,پ";
    public static final String CHAR_N="01223456789+-*/][?><=)(@#$%^&!:۰۱۲۳۴۵۶۷۸۹";

    public static final String CHAR= CHAR_rus + CHAR_bangali + CHAR_Georgian + CHAR_Hebrew + CHAR_Greek + CHAR_E + CHAR_F + CHAR_N;
    public static final String CHARy = CHAR_rus + CHAR_bangali + CHAR_Georgian + CHAR_Hebrew + CHAR_Greek + CHAR_E + CHAR_F + CHAR_N;

    CreateChars chs=new CreateChars();
    ////////////////////////////////////////////
    ///////////////////////////////////////////

    public String InCodeSms(String sms,String Code){
        String smsTemp = "";
        int[] x = chs.CreateFinalCode(chs.anlizmCode(chs.anlizCode(Code)));
        String Chars =chs.CreateChars(x);
        String ch=CHAR.replace(",","");
        for(int i=0;i<sms.length();i++)
        {
            if(ch.indexOf(sms.charAt(i))!=-1){
                smsTemp = smsTemp + Chars.charAt(ch.indexOf(sms.charAt(i)));
            }else{
                smsTemp=smsTemp+sms.charAt(i);
            }
        }
        return smsTemp;
    }
}
