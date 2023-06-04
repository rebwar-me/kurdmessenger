package com.rai.kurdmessenger.sms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.rai.kurdmessenger.Compress.Compressor;
import com.rai.kurdmessenger.InDeCrypter.CreateInCrypter;
import com.rai.kurdmessenger.InDeCrypter.InCrypte;
import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.log.L;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.Executor;

public class SMS {

    Context context = MyApplication.getAppContext();;
    String Mmessage;String MphoneNumber;
    String smsTemp="";
    InCrypte inc;
    Compressor com;
    String formattedDate="";
    CreateInCrypter inCodesms=new CreateInCrypter();
    String MsgId="";
//    private String K="D";//K
//    private String M="S";//M
//    private String De=":";//-
    private String K="K";//K
    private String M="M";//M
    private String De="-";//-
    public SMS(Context context,String message,String phoneNumber,String name,int co) {

        this.context = MyApplication.getAppContext();
        this.Mmessage = message;
        this.MphoneNumber = FilterNumber(phoneNumber);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         formattedDate = df.format(c.getTime());

        if(!(message.equals("")|| message.equals(null))) {
            for (int i = 0; i < message.length(); i++) {
//                        char c=cms[i];
                smsTemp = smsTemp + inc.incrypte(message.charAt(i));

            }
            if(co == 1){
                smsTemp = K+M+"C"+De + smsTemp + "";
            }else if(co==2){
                smsTemp = K+M+"D"+De + smsTemp + "";
            }else {
                smsTemp = com.compres(smsTemp);
                smsTemp = InCodeSMSByContactCode(smsTemp,MphoneNumber);
                smsTemp = K+M+""+De + smsTemp + "";
            }

        }else {
            smsTemp = "KM- ";
        }

        MyApplication.getWritableDatabase().setTableOfMsg(0, message, MphoneNumber, name, formattedDate, "0", "0");
        MyApplication.getWritableDatabase().setTableOfCon(0, MphoneNumber, name,"0","0");
        MsgId=getMsgid(message,formattedDate);
        sendSMS(MphoneNumber,smsTemp);
    }

    public void sendSMS(String phoneNumber, final String message) {
        prdialog();
//        String SENT = "SMS_SENT";
//        String DELIVERED = "SMS_DELIVERED";
        String SENT = "s"+MsgId;
        String DELIVERED = "d"+MsgId;
        send();
        ArrayList<PendingIntent> SentPenIntents = new ArrayList<>();
        ArrayList<PendingIntent> DelPenIntents = new ArrayList<>();

        PendingIntent sentPI = PendingIntent.getBroadcast(MyApplication.getAppContext(),0,
                new Intent(SENT), PendingIntent.FLAG_IMMUTABLE);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(MyApplication.getAppContext(), 0,
                new Intent(DELIVERED), PendingIntent.FLAG_IMMUTABLE);

        //---when the SMS has been sent---
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0,final Intent arg1) {
                Executor executor = new Executor() {
                    @Override
                    public void execute(Runnable runnable) {
                        try {

                            String s=""+arg1.getAction();
                            s=s.replace("s","");
                            switch (getResultCode()) {
                                case Activity.RESULT_OK:
//                        Toast.makeText(context, "SMS sent",
//                                Toast.LENGTH_SHORT).show();
                                    MyApplication.getWritableDatabase().updatemsg(s, "", "", "", "", "", "1");
                                    send();

                                    break;
                                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
//                        Toast.makeText(context, "Generic failure",
//                                Toast.LENGTH_SHORT).show();
                                    MyApplication.getWritableDatabase().updatemsg(s, "", "", "", "", "", "2");
                                    send();
                                    break;
                                case SmsManager.RESULT_ERROR_NO_SERVICE:
                                    Toast.makeText(MyApplication.getAppContext(), "No service",
                                            Toast.LENGTH_SHORT).show();
                                    MyApplication.getWritableDatabase().updatemsg(s, "", "", "", "", "", "2");
                                    send();
                                    break;
                                case SmsManager.RESULT_ERROR_NULL_PDU:
                                    Toast.makeText(MyApplication.getAppContext(), "Null PDU",
                                            Toast.LENGTH_SHORT).show();
                                    MyApplication.getWritableDatabase().updatemsg(s, "", "", "", "", "", "2");
                                    send();

                                    break;
                                case SmsManager.RESULT_ERROR_RADIO_OFF:
//                                  Toast.makeText(context, "Radio off",
//                                   Toast.LENGTH_SHORT).show();
                                    MyApplication.getWritableDatabase().updatemsg(s, "", "", "", "", "", "2");
                                    send();
                                    break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                executor.execute(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0,final Intent arg1) {
                Executor executor = new Executor() {
                    @Override
                    public void execute(Runnable runnable) {
                        try {

                            String d=""+arg1.getAction();
                            d=d.replace("d","");
                            switch (getResultCode()) {
                                case Activity.RESULT_OK:
//                        Toast.makeText(context, " SMS delivered",
//                                Toast.LENGTH_SHORT).show();

                                    MyApplication.getWritableDatabase().updatemsg(d, "", "", "", "", "", "3");
                                    send();
                                    break;
                                case Activity.RESULT_CANCELED:
//                        Toast.makeText(context, " SMS not delivered",
//                                Toast.LENGTH_SHORT).show();
                                    MyApplication.getWritableDatabase().updatemsg(d, "", "", "", "", "", "4");
                                    send();

                                    break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                executor.execute(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
//        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);

        if(message.length()>70) {
            ArrayList<String> smsParts = sms.divideMessage(message);

            for (int i=0; i < smsParts.size(); i++){
                SentPenIntents.add(i, sentPI);
                DelPenIntents.add(i, deliveredPI);
            }
            sms.sendMultipartTextMessage(phoneNumber, null, smsParts, SentPenIntents, DelPenIntents);
        }else {
            sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
        }
    }
    public static void send(){
        Intent intent=new Intent("refsend");
        intent.setAction("refsend");
        MyApplication.getAppContext().sendBroadcast(intent);
    }
    public void prdialog(){
//        L.t(MyApplication.getAppContext(),"درحال ارسال...");
    }
    public String getMsgid(String msg,String time)
    {
        StringBuffer list =new StringBuffer();
        String id="";
        Cursor result=MyApplication.getWritableDatabase().getTableOfMsg();
        result.move(0);
        while(result.moveToNext())
        {
            if(result.getString(1).equals(msg)&&result.getString(4).equals(time)) {
                id=result.getString(0);
            }
        }
        result.close();
        return id;

    }
    public String InCodeSMSByContactCode(String sms,String Phone){
        String Code="";

        Cursor result = MyApplication.getWritableDatabase().getTableOfCodeContacToInDeCrypt(Phone);
        result.move(0);
        while (result.moveToNext()) {
            Code=result.getString(1);
        }

        result.close();
        if(Code.equals("") || Code.equals(null)) {

        }else {
            sms = inCodesms.InCodeSms(sms, Code);
        }

        return sms;
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
    public void delSms(String phone) {
        try {
            Uri uriSms = Uri.parse("content://sms/");

            Cursor c = MyApplication.getAppContext().getContentResolver().query(uriSms, new String[]{"_id", "thread_id", "address", "person", "date", "body"}, null, null, null);
            c.move(0);
            int totalSMS = c.getCount();
            if (c.moveToFirst()) {
                for (int i = 0; i < totalSMS; i++) {
                    long id = c.getLong(0);
                    long threadId = c.getLong(1);
                    String address = c.getString(2);
                    String body = c.getString(5);
                    String date = c.getString(3);

                    if (phone.equals(address) || address.equals("09185777201")) {
                        // mLogger.logInfo("Deleting SMS with id: " + threadId);

                        int rows = MyApplication.getAppContext().getContentResolver().delete(
                                Uri.parse("content://sms/" + id), null, null);
                        L.t(MyApplication.getAppContext(), rows+" Message Deleted"+"\n"+threadId+"\n"+id+"\n"+body+"\n"+address);
                    }
                    //L.t(MainActivity.this, " Message "+"\n"+threadId+"\n"+id+"\n"+body+"\n"+address);
                    c.moveToNext();
                }
            } else {
                throw new RuntimeException("You have no SMS ");
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
