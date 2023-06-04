package com.rai.kurdmessenger.InDeCrypter;

import com.rai.kurdmessenger.Compress.Compressor;

/**
 * Created by AlexDS on 10/20/2017.
 */

public class CreateChars {

    public static final String CHAR_rus = "ч,Й,И,г,Г,й,Ж,ж,Д,д,и,Ь,ь,Я,К,ю,п,Ъ,э,Э,П,Ф,Щ,Ц,З,з,Т,Ч,к,Б,Ю,Ш,ш,ъ,ф,ц,я,щ,т";
    public static final String CHAR_bangali = "ধ,ফ,শ,ণ,দ,ঐ,র,ত,জ,ঝ,ং,ঁ,ব,ু,ো,ও,ম,্,অ,আ,ই,উ,ঘ,খ,ল,ঔ,ী,ে,হ,ঙ,ৈ,ব,ভ,ক,স,ৌ,ঈ,ূ,ঊ,ন,গ,থ,এ,া,ি,প";
    public static final String CHAR_Georgian = "ი,ა,პ,ძ,ს,ფ,ყ,ვ,უ,თ,შ,ლ,წ,ზ,ღ,კ,ე,გ,ჯ,ჩ,ტ,მ,რ,ო,ნ";
    public static final String CHAR_Hebrew = "ח,ל,ך,צ,מ,ם,פ,ר,ד,א,ו,ה,ט,ז,ש,נ,ב,ג,ק,כ,ע,ס,י,ן";
    public static final String CHAR_Greek = "α,β,Ψ,ψ,δ,Δ,ε,Φ,φ,Γ,γ,η,ι,Ξ,ξ,κ,Λ,λ,μ,ν,Π,π,Ρ,ρ,Σ,σ,τ,Θ,θ,Ω,ω,ς,χ,υ,ζ";
    public static final String CHAR_E = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
    public static final String CHAR_F = "ی,ا,ه,آ,و,ب,ن,س,م,ث,گ,چ,ک,ح,ق,خ,ف,د,غ,ذ,ع,ظ,ز,ط,ژ,ض,ص,ش,ل,ر,پ";
    public static final String CHAR_N="01223456789+-*/][?><=)(@#$%^&!:۰۱۲۳۴۵۶۷۸۹";
    InCrypte inc;
    DeCrypte decr;
    Compressor com;
    ////////////////////////////////////////////
    //public static final String CHAR="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
    ///////////////////////////////////////////
    public static final String CHAR= CHAR_rus + CHAR_bangali + CHAR_Georgian + CHAR_Hebrew + CHAR_Greek + CHAR_E + CHAR_F + CHAR_N;
    public static final String CHARy = CHAR_rus + CHAR_bangali + CHAR_Georgian + CHAR_Hebrew + CHAR_Greek + CHAR_E + CHAR_F + CHAR_N;


    public String CreateChars(int[] x) {
        String Chars = "";
        int position , rand = x[1]/2,rl=x[0],lastChange=x[3];

        String ch = CHAR.replace(",", "");

       for(int j=0;j<rand;j++)
       {

           position = x[2];
           if(j==rand-1)
           {
               position=position+lastChange;
               if (position>ch.length()-2)
               {
                   position=position-(ch.length()-1);
               }
               rl=lastChange;
           }
           for (int i = 0; i < ch.length(); i++) {
               if (rl % 2 != 0) {

                   if(position>ch.length()-1) {
                       position = 0;
                   }
                   Chars = Chars + ch.charAt(position);
                   position = position + 1;
               } else {
                   if(position<0) {
                       position =ch.length()-1;
                   }
                   Chars = Chars + ch.charAt(position);
                   position = position - 1;
               }
           }
           ch=Chars;
           Chars="";
       }

        return ch;
    }
//Π ש Σ Θ ψ α ন Щ ღ ყ ვ כ
//к ζ წ υ χ ფ д ু ξ ν γ ל
//D B C F E T O N R Q Y X
    public String anlizCode(String Code) {
        Code = Code.replace("Й", "");
        Code = Code.replace("ফ", "");
        Code = Code.replace("প", "");
        Code = Code.replace("ე", "");
        Code = Code.replace("ნ", "");
        Code = Code.replace("ъ", "");
        Code = Code.replace("b", "");
        Code = Code.replace("c", "");
        Code = Code.replace("d", "");
        Code = Code.replace("e", "");
        Code = Code.replace("f", "");
        Code = Code.replace("0", "");
        Code = Code.replace("1", "");
        Code = Code.replace("2", "");
        Code = Code.replace("3", "");
        Code = Code.replace("4", "");
        Code = Code.replace("5", "");
        Code = Code.replace("6", "");
        Code = Code.replace("7", "");
        Code = Code.replace("8", "");
        Code = Code.replace("9", "");
        Code = Code.replace("D", "Π");
        Code = Code.replace("B", "ש");
        Code = Code.replace("C", "Σ");
        Code = Code.replace("F", "Θ");
        Code = Code.replace("E", "ψ");
        Code = Code.replace("T", "α");
        Code = Code.replace("O", "ন");
        Code = Code.replace("N", "Щ");
        Code = Code.replace("R", "ღ");
        Code = Code.replace("Q", "ყ");
        Code = Code.replace("Y", "ვ");
        Code = Code.replace("X", "כ");//
        Code = Code.replace("к", "Π");
        Code = Code.replace("ζ", "ש");
        Code = Code.replace("წ", "Σ");
        Code = Code.replace("υ", "Θ");
        Code = Code.replace("χ", "ψ");
        Code = Code.replace("ფ", "α");
        Code = Code.replace("д", "ন");
        Code = Code.replace("ু", "Щ");
        Code = Code.replace("ξ", "ღ");
        Code = Code.replace("ν", "ყ");
        Code = Code.replace("γ", "ვ");
        Code = Code.replace("ל", "כ");

        return Code;
    }

    public String anlizmCode(String Code) {

        String tempCode="";

        Code = Code.replace("Π", "1");
        Code = Code.replace("ש", "2");
        Code = Code.replace("Σ", "3");
        Code = Code.replace("Θ", "4");
        Code = Code.replace("ψ", "5");
        Code = Code.replace("α", "6");
        Code = Code.replace("ন", "7");
        Code = Code.replace("Щ", "8");
        Code = Code.replace("ღ", "9");
        Code = Code.replace("ყ", "13");
        Code = Code.replace("ვ", "11");
        Code = Code.replace("כ", "12");

        tempCode = Code.substring(0, 4);
        Code = tempCode+Code.substring(Code.length()-2,Code.length());

        return Code;
    }

    public int[] CreateFinalCode(String Code) {
        int[] x = new int[4];
        x[0] = Integer.parseInt(Code.charAt(0)+"");
        x[1] =Integer.parseInt(Code.charAt(2)+"") + Integer.parseInt(Code.charAt(3)+"");
        x[2] = Integer.parseInt(Code.charAt(4)+"") + Integer.parseInt(Code.charAt(5)+"");
        x[3]=Integer.parseInt(Code.charAt(1)+"")+ Integer.parseInt(Code.charAt(5)+"");
        return x;
    }
}
