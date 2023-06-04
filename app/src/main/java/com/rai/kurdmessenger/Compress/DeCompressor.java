package com.rai.kurdmessenger.Compress;

/**
 * Created by alex on 01/01/2009.
 */

public class DeCompressor {

    public static String decompres(String sms){



        ///////////////////////////////////7harfi
        /////////bangali a A b B
        sms=sms.replace("ধ","QWCMFWA");// Oدانشگاه
        sms=sms.replace("ফ","nWCBWQA");//خانوادهH

        /////////bangali a A b B


        ///////////////////////////////////6harfi
        //////////rus
        sms=sms.replace("ч","آمpeeخ");
        sms=sms.replace("Alex","pآاحیp");
        //////////rus
        /////////bangali a A b B
        sms=sms.replace("শ","WMتVWA");//  Mاشتباه

        /////////bangali a A b B


        ///////////////////////////////////5harfi
        //////////rus
        sms=sms.replace("Й","رداب");
        sms=sms.replace("И","مcpیم");
        sms=sms.replace("г","WDپBO");
        sms=sms.replace("Г","GTWqq");
        //////////rus
        /////////bangali a A b B
        sms=sms.replace("ণ","VWپWC");    // c باران// c باران
        sms=sms.replace("দ","DQپNA");//  oمدرسه

        ////////////////////////////////////////////Georgian abc
        sms=sms.replace("ი","WKEWJ");//  nاطلاع

        /////////bangali a A b B

        ///////////////////////////////////4harfi
        //////////rus
        sms=sms.replace("й","جیمی");
        sms=sms.replace("Ж","DWMW");
        sms=sms.replace("ж","NWJت");
        sms=sms.replace("Д","گییچ");
        sms=sms.replace("д","گبیم");
        sms=sms.replace("и","تWNپ");
        sms=sms.replace("Ь","zپQW");
        sms=sms.replace("ь","ACBO");
        sms=sms.replace("Я","DیپD");
        sms=sms.replace("К","NEWD");
        sms=sms.replace("ю","JOqO");
        sms=sms.replace("п","QBNت");

        //////////rus
        /////////bangali a A b B
        sms=sms.replace("ঐ","uBWN");// Wحواس
        sms=sms.replace("র","DCKH");// jمنطق
        sms=sms.replace("ত","HMCF");// lقشنگ
        sms=sms.replace("জ","HپWپ");// pقرار
        sms=sms.replace("ঝ","MCWN");//Pشناس
        sms=sms.replace("ং","TOBA");//xجزوه
        sms=sms.replace("ঁ","GتWV");//Xکتاب
        sms=sms.replace("ব","HپDO");  //قرمزb
        sms=sms.replace("ু","nqEq");//خیلی  g

        /////////bangali a A b B
        ///////////////////////////Hebrew abc
        sms=sms.replace("ח","nBWV");// jخواب
        sms=sms.replace("ל","GEDA");// kکلمه
        sms=sms.replace("ך","DQWD");//lمدام
        sms=sms.replace("צ","TBWV");//mجواب
        sms=sms.replace("מ","uDWD");//  nحمام
        sms=sms.replace("ם","HVBE");// oقبول
        sms=sms.replace("פ","VWAD");//  pباهم
        sms=sms.replace("ר","EuyA");// rلحضه
        sms=sms.replace("ד","CBMت");//sنوشت
        sms=sms.replace("א","SWپA");//tچاره
        sms=sms.replace("ו","TWQA");//  uجاده
        sms=sms.replace("ה","AپSq");// vهرچی
        sms=sms.replace("ט","WEWC");// yالان
        sms=sms.replace("ז","تWOA");//zتازه
        ///////////////////////////Greek abc
        ////////////////////////////////////////////Georgian abc
        sms=sms.replace("ა","DBWQ");//  gمواد
        sms=sms.replace("პ","WBپQ");// hاورد
        sms=sms.replace("ძ","VWEW");//sبالا
        sms=sms.replace("ს","EVWN");// v لباس


        ///////////////////////////////////3harfi
        //////////rus
        sms=sms.replace("Ъ","oqV");
        sms=sms.replace("э","شuع");
        sms=sms.replace("Э","mnE");
        sms=sms.replace("П","گon");
        sms=sms.replace("Ф","VپW");
        sms=sms.replace("Щ","AWت");
        sms=sms.replace("Ц","خسم");
        sms=sms.replace("З","کحا");
        sms=sms.replace("з","درو");
        sms=sms.replace("Т","nQW");
        sms=sms.replace("Ч","سeث");
        sms=sms.replace("к","nBV");
        sms=sms.replace("Б","DپQ");
        sms=sms.replace("Ю","سبا");
        sms=sms.replace("Ш","AqS");
        sms=sms.replace("ш","Nnت");
        sms=sms.replace("ъ","QWپ");
        sms=sms.replace("П","WFپ");
        sms=sms.replace("ф","VWM");
        sms=sms.replace("ц","WپA");
        //////////rus
/////////bangali a A b B
        sms=sms.replace("ো","OپQ");//  زرد  a
        sms=sms.replace("ও","NVO");  //A سبز
        sms=sms.replace("ম","WVپ");//  ابرC
        sms=sms.replace("্","NپQ");// d سرد
        sms=sms.replace("অ","FپD");// D گرم
        sms=sms.replace("আ","nVپ");//خبر  E
        sms=sms.replace("ই","WNت");// است   F
        sms=sms.replace("উ","qWQ");//  یاد  G
        sms=sms.replace("ঘ","JHE");//  عقل I
        sms=sms.replace("খ","THQ");// Kجدا
        sms=sms.replace("ল","ABW");// nهوا
        sms=sms.replace("ঔ","VWپ");//  Qبار
        sms=sms.replace("ী","VWq");// rبای
        sms=sms.replace("ে","uپz");// sحرف
        sms=sms.replace("হ","QNت");//  uدست
        sms=sms.replace("ঙ","Cyپ");// Uنظر
        sms=sms.replace("ৈ","uQN");// wحدس
        sms=sms.replace("ব","SMD");//  yچشم
        sms=sms.replace("ভ","TCN");//  Yجنس
        sms=sms.replace("ক","JMH");//  kعشق
        sms=sms.replace("স","IEK");//mغلط
        sms=sms.replace("ৌ","GWپ");//qکار
        sms=sms.replace("ঈ","رWG");//  Rپاک
        sms=sms.replace("ূ","MDW");//tشما
        sms=sms.replace("ঊ","SCQ");//Tچند
        sms=sms.replace("ন","VپQ");//  vبرد
        sms=sms.replace("গ","nBپQ");// خورد  i

/////////bangali a A b B
        ///////////////////////////Greek abc
        sms=sms.replace("α","OBQ");// aزود
        sms=sms.replace("β","zGپ");//  bفکر
        sms=sms.replace("Ψ","GzM");// Cکفش
        sms=sms.replace("ψ","nBQ");// cخود
        sms=sms.replace("δ","nBW");//dخوا
        sms=sms.replace("Δ","Jxپ");//Dعصر
        sms=sms.replace("ε","JxV");//  eعصب
        sms=sms.replace("Φ","xVu");// Fصبح
        sms=sms.replace("φ","VWO");//  fباز
        sms=sms.replace("Γ","VIE");// Gبغل
        sms=sms.replace("γ","nBM");// gخوش
        sms=sms.replace("η","uWE");//hحال
        sms=sms.replace("ι","Fzت");//iگفت
        sms=sms.replace("Ξ","CzN");//  Jنفس
        sms=sms.replace("ξ","GDG");// jکمک
        sms=sms.replace("κ","CQA");//  kنده
        sms=sms.replace("Λ","Sپw");// Lچرا
        sms=sms.replace("λ","QWC");// lدان
        sms=sms.replace("μ","تWC");//mتان
        sms=sms.replace("ν","FBM");//nگوش
        sms=sms.replace("Π","تپN");//  Pترس
        sms=sms.replace("π","HBE");// pقول
        sms=sms.replace("Ρ","HQD");//  Rقدم
        sms=sms.replace("ρ","QBC");// rدون
        sms=sms.replace("Σ","WBC");// Sاون
        sms=sms.replace("σ","CBM");//sنوش
        sms=sms.replace("τ","OCF");//tزنگ
        sms=sms.replace("Θ","VBQ");//  Uبود
        sms=sms.replace("θ","HxQ");//  uقصد
        sms=sms.replace("Ω","HND");// Vقسم
        sms=sms.replace("ω","پCF");//vرنگ
        sms=sms.replace("ς","SHQ");//wچقد
        sms=sms.replace("χ","DWA");//  xماه
        sms=sms.replace("υ","TBپ");//  yجور
        sms=sms.replace("ζ","VJQ");//  zبعد
        ///////////////////////////Greek abc
        ///////////////////////////Hebrew abc
        sms=sms.replace("ש","SBC");// aچون
        sms=sms.replace("נ","WOت");//  bازت
        sms=sms.replace("ב","MWD");// cشام
        sms=sms.replace("ג","Czپ");// dنفر
        sms=sms.replace("ק","nBW");//eنها
        sms=sms.replace("כ","VFB");//fبگو
        sms=sms.replace("ע","CDq");//  gنمی
        sms=sms.replace("ס","VWC");// xبان

        ///////////////////////////////////////////Georgian abc
        sms=sms.replace("ფ","VOC");// aبزن
        sms=sms.replace("ყ","VپB");// cبرو
        sms=sms.replace("ვ","CWG");// dناک
        sms=sms.replace("უ","QqQ");//eدید
        sms=sms.replace("თ","VqC");//fبین
        sms=sms.replace("შ","رBE");//  iپول
        sms=sms.replace("ლ","QBپ");//lدور
        sms=sms.replace("წ","VqW");// oبیا
        sms=sms.replace("ზ","NGN");//  pسکس
        sms=sms.replace("ღ","JHV");// qعقب
        sms=sms.replace("კ","TEB");// rجلو
        sms=sms.replace("ე","GپQ");//tکرد
        sms=sms.replace("გ","VAM");//  uبهش
        sms=sms.replace("ჯ","BWپ");//  wوار
        sms=sms.replace("ჩ","NJq");// xسعی
        sms=sms.replace("ჭ","nBC");//zخون
        sms=sms.replace("ტ","QBQ");//mدود
        ///////////////////////////////////2harfi
        //////////rus
        sms=sms.replace("я","GC");
        sms=sms.replace("щ","VQ");
        sms=sms.replace("т","Qپ");
        //////////rus
/////////bangali a A b B
        sms=sms.replace("থ","NF");//Lسگ
        sms=sms.replace("এ","VW");// Sبا
        sms=sms.replace("া","FE");// e گل
        sms=sms.replace("ি","DC");   //منf
        sms=sms.replace("প","تB");// تو   h

        ///////////////////////////Hebrew abc
        sms=sms.replace("י","CW");// hنا
        sms=sms.replace("ן","CB");//  iنو
        //////////////////////////////////////////Georgian abc
        sms=sms.replace("მ","Gq");// bکی
        sms=sms.replace("რ","OC");// jزن
        sms=sms.replace("ო","nپ");// kخر
        sms=sms.replace("ნ","OQ");// yزد


/////////bangali a A b B

        return sms;
    }
}
