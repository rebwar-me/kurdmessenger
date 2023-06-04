package com.rai.kurdmessenger.InDeCrypter;

/**
 * Created by DS on 11/02/2017.
 */

public class CodeInDeCrypter {

    String CodeInDeCrypter="RRTTTR";

    CreateInCrypter cic=new CreateInCrypter();
    CreateDeCrypter cdc=new CreateDeCrypter();

    public String CodeInCrypter(String Code,String Phone){
        String cicCode="";

        for (int i=0;i<Code.length();i++)
        {
            if(i<=7)
            {
                cicCode=cicCode+Code.charAt(i);
            }else if(i==8){
                cicCode=cicCode+"@"+Phone+"@"+Code.charAt(i);
            }else {
                cicCode=cicCode+Code.charAt(i);
            }
        }

        cicCode=cic.InCodeSms(cicCode,CodeInDeCrypter);

       // return Code+"\n\n"+temp_Code+"\n\n"+cic.InCodeSms(temp_Code,CodeInDeCrypter)+"\n\n"+cdc.DeCodeSms(cic.InCodeSms(temp_Code,CodeInDeCrypter),CodeInDeCrypter);
        return cicCode;
    }

    public String CodeDeCrypter(String Code){

        String cdcCode="";
        String MaineCode="";

        cdcCode=cdc.DeCodeSms(Code,CodeInDeCrypter);
        MaineCode=cdcCode.substring(0,8)+cdcCode.substring(cdcCode.length()-8,cdcCode.length());

        return MaineCode;
    }
    public String CodePhoneDeCrypter(String Code){

        String cdcCode="";
        String Phone="";

        cdcCode=cdc.DeCodeSms(Code,CodeInDeCrypter);
        Phone=cdcCode.replace(cdcCode.substring(0,8),"");
        Phone=Phone.replace(Phone.substring(Phone.length()-8,Phone.length()),"");
        Phone=Phone.replace("@","");

        return Phone;
    }
}
