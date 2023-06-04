package com.rai.kurdmessenger.Compress;

/**
 * Created Created by @rebwar_me.
 */
public class Compressor {

        public static String compres(String sms){

//////////////////////////////////////////////////////////////

            ///////////////////////////////////7harfi
            /////////bangali a A b B
            sms=sms.replace("QWCMFWA","ধ");// Oدانشگاه
            sms=sms.replace("nWCBWQA","ফ");//خانوادهH

            /////////bangali a A b B


            ///////////////////////////////////6harfi
            //////////rus
            sms=sms.replace("آمpeeخ","ч");
            sms=sms.replace("pآاحیp","Alex");
            //////////rus
            /////////bangali a A b B
            sms=sms.replace("WMتVWA","শ");//  Mاشتباه

            /////////bangali a A b B


            ///////////////////////////////////5harfi
            //////////rus
            sms=sms.replace("رداب","Й");
            sms=sms.replace("مcpیم","И");
            sms=sms.replace("WDپBO","г");
            sms=sms.replace("GTWqq","Г");
            //////////rus
            /////////bangali a A b B
            sms=sms.replace("VWپWC","ণ");    // c باران// c باران
            sms=sms.replace("DQپNA","দ");//  oمدرسه

            ////////////////////////////////////////////Georgian abc
            sms=sms.replace("WKEWJ","ი");//  nاطلاع

            ///////////////////////////////////4harfi
            //////////rus
            sms=sms.replace("جیمی","й");
            sms=sms.replace("DWMW","Ж");
            sms=sms.replace("NWJت","ж");
            sms=sms.replace("گییچ","Д");
            sms=sms.replace("گبیم","д");
            sms=sms.replace("تWNپ","и");
            sms=sms.replace("zپQW","Ь");
            sms=sms.replace("ACBO","ь");
            sms=sms.replace("DیپD","Я");
            sms=sms.replace("NEWD","К");
            sms=sms.replace("JOqO","ю");
            sms=sms.replace("QBNت","п");

            //////////rus
            /////////bangali a A b B
            sms=sms.replace("uBWN","ঐ");// Wحواس
            sms=sms.replace("DCKH","র");// jمنطق
            sms=sms.replace("HMCF","ত");// lقشنگ
            sms=sms.replace("HپWپ","জ");// pقرار
            sms=sms.replace("MCWN","ঝ");//Pشناس
            sms=sms.replace("TOBA","ং");//xجزوه
            sms=sms.replace("GتWV","ঁ");//Xکتاب
            sms=sms.replace("HپDO","ব");  //قرمزb
            sms=sms.replace("nqEq","ু");//خیلی  g

            /////////bangali a A b B
            ///////////////////////////Hebrew abc
            sms=sms.replace("nBWV","ח");// jخواب
            sms=sms.replace("GEDA","ל");// kکلمه
            sms=sms.replace("DQWD","ך");//lمدام
            sms=sms.replace("TBWV","צ");//mجواب
            sms=sms.replace("uDWD","מ");//  nحمام
            sms=sms.replace("HVBE","ם");// oقبول
            sms=sms.replace("VWAD","פ");//  pباهم
            sms=sms.replace("EuyA","ר");// rلحضه
            sms=sms.replace("CBMت","ד");//sنوشت
            sms=sms.replace("SWپA","א");//tچاره
            sms=sms.replace("TWQA","ו");//  uجاده
            sms=sms.replace("AپSq","ה");// vهرچی
            sms=sms.replace("WEWC","ט");// yالان
            sms=sms.replace("تWOA","ז");//zتازه
            ///////////////////////////Greek abc
            ////////////////////////////////////////////Georgian abc
            sms=sms.replace("DBWQ","ა");//  gمواد
            sms=sms.replace("WBپQ","პ");// hاورد
            sms=sms.replace("VWEW","ძ");//sبالا
            sms=sms.replace("EVWN","ს");// v لباس



            ///////////////////////////////////3harfi
            //////////rus
            sms=sms.replace("oqV","Ъ");
            sms=sms.replace("شuع","э");
            sms=sms.replace("mnE","Э");
            sms=sms.replace("گon","П");
            sms=sms.replace("VپW","Ф");
            sms=sms.replace("AWت","Щ");
            sms=sms.replace("خسم","Ц");
            sms=sms.replace("کحا","З");
            sms=sms.replace("درو","з");
            sms=sms.replace("nQW","Т");
            sms=sms.replace("سeث","Ч");
            sms=sms.replace("nBV","к");
            sms=sms.replace("DپQ","Б");
            sms=sms.replace("سبا","Ю");
            sms=sms.replace("AqS","Ш");
            sms=sms.replace("Nnت","ш");
            sms=sms.replace("QWپ","ъ");
            sms=sms.replace("VWM","ф");
            sms=sms.replace("WپA","ц");
            //////////rus
/////////bangali a A b B
            sms=sms.replace("OپQ","ো");//  زرد  a
            sms=sms.replace("NVO","ও");  //A سبز
            sms=sms.replace("WVپ","ম");//  ابرC
            sms=sms.replace("NپQ","্");// d سرد
            sms=sms.replace("FپD","অ");// D گرم
            sms=sms.replace("nVپ","আ");//خبر  E
            sms=sms.replace("WNت","ই");// است   F
            sms=sms.replace("qWQ","উ");//  یاد  G
            sms=sms.replace("JHE","ঘ");//  عقل I
            sms=sms.replace("THQ","খ");// Kجدا
            sms=sms.replace("ABW","ল");// nهوا
            sms=sms.replace("VWپ","ঔ");//  Qبار
            sms=sms.replace("VWq","ী");// rبای
            sms=sms.replace("uپz","ে");// sحرف
            sms=sms.replace("QNت","হ");//  uدست
            sms=sms.replace("Cyپ","ঙ");// Uنظر
            sms=sms.replace("uQN","ৈ");// wحدس
            sms=sms.replace("SMD","ব");//  yچشم
            sms=sms.replace("TCN","ভ");//  Yجنس
            sms=sms.replace("JMH","ক");//  kعشق
            sms=sms.replace("IEK","স");//mغلط
            sms=sms.replace("GWپ","ৌ");//qکار
            sms=sms.replace("رWG","ঈ");//  Rپاک
            sms=sms.replace("MDW","ূ");//tشما
            sms=sms.replace("SCQ","ঊ");//Tچند
            sms=sms.replace("VپQ","ন");//  vبرد
            sms=sms.replace("nBپQ","গ");// خورد  i

/////////bangali a A b B
            ///////////////////////////Greek abc
            sms=sms.replace("OBQ","α");// aزود
            sms=sms.replace("zGپ","β");//  bفکر
            sms=sms.replace("GzM","Ψ");// Cکفش
            sms=sms.replace("nBQ","ψ");// cخود
            sms=sms.replace("nBW","δ");//dخوا
            sms=sms.replace("Jxپ","Δ");//Dعصر
            sms=sms.replace("JxV","ε");//  eعصب
            sms=sms.replace("xVu","Φ");// Fصبح
            sms=sms.replace("VWO","φ");//  fباز
            sms=sms.replace("VIE","Γ");// Gبغل
            sms=sms.replace("nBM","γ");// gخوش
            sms=sms.replace("uWE","η");//hحال
            sms=sms.replace("Fzت","ι");//iگفت
            sms=sms.replace("CzN","Ξ");//  Jنفس
            sms=sms.replace("GDG","ξ");// jکمک
            sms=sms.replace("CQA","κ");//  kنده
            sms=sms.replace("Sپw","Λ");// Lچرا
            sms=sms.replace("QWC","λ");// lدان
            sms=sms.replace("تWC","μ");//mتان
            sms=sms.replace("FBM","ν");//nگوش
            sms=sms.replace("تپN","Π");//  Pترس
            sms=sms.replace("HBE","π");// pقول
            sms=sms.replace("HQD","Ρ");//  Rقدم
            sms=sms.replace("QBC","ρ");// rدون
            sms=sms.replace("WBC","Σ");// Sاون
            sms=sms.replace("CBM","σ");//sنوش
            sms=sms.replace("OCF","τ");//tزنگ
            sms=sms.replace("VBQ","Θ");//  Uبود
            sms=sms.replace("HxQ","θ");//  uقصد
            sms=sms.replace("HND","Ω");// Vقسم
            sms=sms.replace("پCF","ω");//vرنگ
            sms=sms.replace("SHQ","ς");//wچقد
            sms=sms.replace("DWA","χ");//  xماه
            sms=sms.replace("TBپ","υ");//  yجور
            sms=sms.replace("VJQ","ζ");//  zبعد
            ///////////////////////////Greek abc
            ///////////////////////////Hebrew abc
            sms=sms.replace("SBC","ש");// aچون
            sms=sms.replace("WOت","נ");//  bازت
            sms=sms.replace("MWD","ב");// cشام
            sms=sms.replace("Czپ","ג");// dنفر
            sms=sms.replace("nBW","ק");//eنها
            sms=sms.replace("VFB","כ");//fبگو
            sms=sms.replace("CDq","ע");//  gنمی
            sms=sms.replace("VWC","ס");// xبان

            ///////////////////////////////////////////Georgian abc
            sms=sms.replace("VOC","ფ");// aبزن
            sms=sms.replace("VپB","ყ");// cبرو
            sms=sms.replace("CWG","ვ");// dناک
            sms=sms.replace("QqQ","უ");//eدید
            sms=sms.replace("VqC","თ");//fبین
            sms=sms.replace("رBE","შ");//  iپول
            sms=sms.replace("QBپ","ლ");//lدور
            sms=sms.replace("VqW","წ");// oبیا
            sms=sms.replace("NGN","ზ");//  pسکس
            sms=sms.replace("JHV","ღ");// qعقب
            sms=sms.replace("TEB","კ");// rجلو
            sms=sms.replace("GپQ","ე");//tکرد
            sms=sms.replace("VAM","გ");//  uبهش
            sms=sms.replace("BWپ","ჯ");//  wوار
            sms=sms.replace("NJq","ჩ");// xسعی
            sms=sms.replace("nBC","ჭ");//zخون
            sms=sms.replace("QBQ","ტ");//mدود
            ///////////////////////////////////2harfi
            //////////rus
            sms=sms.replace("GC","я");
            sms=sms.replace("VQ","щ");
            sms=sms.replace("Qپ","т");
            //////////rus
/////////bangali a A b B
            sms=sms.replace("NF","থ");//Lسگ
            sms=sms.replace("VW","এ");// Sبا
            sms=sms.replace("FE","া");// e گل
            sms=sms.replace("DC","ি");   //منf
            sms=sms.replace("تB","প");// تو   h

            ///////////////////////////Hebrew abc
            sms=sms.replace("CW","י");// hنا
            sms=sms.replace("CB","ן");//  iنو
            //////////////////////////////////////////Georgian abc
            sms=sms.replace("Gq","მ");// bکی
            sms=sms.replace("OC","რ");// jزن
            sms=sms.replace("nپ","ო");// kخر
            sms=sms.replace("OQ","ნ");// yزد



            return sms;
        }
}
