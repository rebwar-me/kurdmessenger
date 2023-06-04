package com.rai.kurdmessenger.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsMessage;
import android.util.Log;

import com.rai.kurdmessenger.Compress.DeCompressor;
import com.rai.kurdmessenger.InDeCrypter.CodeInDeCrypter;
import com.rai.kurdmessenger.InDeCrypter.CreateChars;
import com.rai.kurdmessenger.InDeCrypter.CreateDeCrypter;
import com.rai.kurdmessenger.InDeCrypter.DeCrypte;
import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.log.L;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.Executor;

/**
 * Created Created by @rebwar_me.
 */

public class SmsReceiver extends BroadcastReceiver
{
	String Name ,Num,Sms;
	static CodeInDeCrypter cidc=new CodeInDeCrypter();
	public static int rindex = 0;
//	private String K="D";//K
//	private String M="S";//M
//	private String De=":";//-

//	private char K='D';//K
//	private char M='S';//M
//	private char De=':';//-
	private char K='K';//K
	private char M='M';//M
	private char De='-';//-
	@Override
	    public void onReceive(Context context, Intent intent)
	       {

	    	//---get the SMS message passed in---
	        Bundle bundle = intent.getExtras();
	        SmsMessage[] msgs = null;
	        String str = "";
	        if (bundle != null)
	        {
	            //---retrieve the SMS message received---
	            Object[] pdus = (Object[]) bundle.get("pdus");
	            msgs = new SmsMessage[pdus.length];
	            for (int i=0; i<msgs.length; i++){
	                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
	                str += "SMS from " + msgs[i].getOriginatingAddress();
					Num=""+msgs[i].getOriginatingAddress();
	                str += " :";
	                str += msgs[i].getMessageBody().toString();
					Sms=""+msgs[i].getMessageBody().toString();
	                str += "\n";
	            }
				Uri personUri = Uri.withAppendedPath( ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Num);

				Cursor cur = context.getContentResolver().query(personUri, new String[] { ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null );

				if( cur.moveToFirst() ) {
					int nameIndex = cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);

					 Name = cur.getString(nameIndex);

				}
				cur.close();
				if((Sms.equals("")|| Sms.equals(null))) {
					Sms=" ";
				}
//				new TaskCompailSMS(Sms,FilterNumber(Num),Name).execute();
//				CompailSMS(Sms,FilterNumber(Num),Name);
	            //---display the new SMS message---

			}

			   Executor executor = new Executor() {
				   @Override
				   public void execute(Runnable runnable) {
					   CompailSMS(Sms,FilterNumber(Num),Name);
				   }
			   };

			   executor.execute(new Runnable() {
				   @Override
				   public void run() {

				   }
			   });
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
	public void  CompailSMS(final String sms,final String num,final String name) {

		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final String formattedDate = df.format(c.getTime());

		final DeCompressor dec = new DeCompressor();
		final DeCrypte decr = new DeCrypte();


		if ((sms.charAt(0)) == K && (sms.charAt(1)) == M && (sms.charAt(2)) == De) {

			try {
				String smsTemp = sms;
				String smsT = dec.decompres(DeCodeSMSByContactCode(smsTemp.substring(3),num));
				smsTemp = "";
				for (int i = 0; i < smsT.length(); i++) {
//                        char c=cms[i];
					smsTemp = smsTemp + decr.decrypte(smsT.charAt(i));
				}
//			L.t(MyApplication.getAppContext(), name+"\n\n" + num + "\n\n"+smsTemp);
				if (name == null) {
					MyApplication.getWritableDatabase().setTableOfMsg(0, smsTemp, num, num, formattedDate, "1", "0");
					MyApplication.getWritableDatabase().setTableOfCon(0, num, num,""+parseNewMsg(num),"0");
				} else {
					MyApplication.getWritableDatabase().setTableOfMsg(0, smsTemp, num, name, formattedDate, "1", "0");
					MyApplication.getWritableDatabase().setTableOfCon(0, num, name,""+parseNewMsg(num),"0");
//				L.t(MyApplication.getAppContext(),MyApplication.getWritableDatabase().setTableOfMsg(0, smsTemp, num, name, formattedDate, "1", "0")+" msg");
//				L.t(MyApplication.getAppContext(),"con "+MyApplication.getWritableDatabase().setTableOfCon(0, num, name,"0"));
				}

				send();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if ((sms.charAt(0)) == K && (sms.charAt(1)) == M && (sms.charAt(2)) == 'C' && sms.charAt(3) == De) {


			String smsTemp = "";
			String Code = "", phone = "", decode = "";
			for (int i = 4; i < sms.length(); i++) {
//                        char c=cms[i];
				smsTemp = smsTemp + decr.decrypte(sms.charAt(i));
			}
//			L.t(MyApplication.getAppContext(), name+"\n\n" + num + "\n\n"+smsTemp);
			MyApplication.getWritableDatabase().del_getTable_CodeReCivide();
			Code = cidc.CodeDeCrypter(smsTemp);
			phone = cidc.CodePhoneDeCrypter(smsTemp);
			Random r = new Random();

			decode = "" + (r.nextInt(9 - 0 + 1) + 0);
			decode = decode + (r.nextInt(9 - 0 + 1) + 0);
			decode = decode + (r.nextInt(9 - 0 + 1) + 0);
			decode = decode + (r.nextInt(9 - 0 + 1) + 0);



			MyApplication.getWritableDatabase().setTable_CodeReCivide(0, Code, decode, num, name);
			new SMS(MyApplication.getAppContext(), decode, phone, phone, 2);
//					L.T(MyApplication.getAppContext(),"code " + decode + "  ri = "+ rindex);
			send();

			//ShortcutBadger.applyCount(MyApplication.getAppContext(), parseNewMsgCount()+1);


		} else if ((sms.charAt(0)) == K && (sms.charAt(1)) == M && (sms.charAt(2)) == 'D' && sms.charAt(3) == De) {
//			Executor executor = new Executor() {
//				@Override
//				public void execute(Runnable runnable) {
//				}
//			};
//
//			executor.execute(new Runnable() {
//				@Override
//				public void run() {
//
//				}
//			});
			try {
				String Code = "", phone = "", decode = "", namesender = "";

				for (int i = 4; i < sms.length(); i++) {
//                        char c=cms[i];
					decode = decode + decr.decrypte(sms.charAt(i));
				}
				Code = getTable_CodeReCivide()[0];
				phone = getTable_CodeReCivide()[2];
				namesender = getTable_CodeReCivide()[3];
				//	L.t(MyApplication.getAppContext(), "" + Code + "\n\n" + phone + "\n\n" + namesender + "\n\n" + getTable_CodeReCivide()[1]);

				if (decode.equals(getTable_CodeReCivide()[1])) {
/////////////////////
					if (!isCodeExist(Code)) {
						if (MyApplication.getWritableDatabase().CodeConter() < 10) {

							MyApplication.getWritableDatabase().setTableOfCode(0, Code);
							MyApplication.getWritableDatabase().deleteContactByCodeToAddHemToNewCode(phone);
							MyApplication.getWritableDatabase().setTableOfCodeContact(0, Code, phone, namesender);


						}else {
							L.t(MyApplication.getAppContext(), "کد ذخیره نشد !!!");
							L.t(MyApplication.getAppContext(), "گنجایش کد پر است.");
						}
					} else {
						if(MyApplication.getWritableDatabase().CodeCountactConter(Code)<10) {
							if (!isCountactExist(Code, phone)) {
								MyApplication.getWritableDatabase().deleteContactByCodeToAddHemToNewCode(phone);
								MyApplication.getWritableDatabase().setTableOfCodeContact(0, Code, phone, namesender);
								L.t(MyApplication.getAppContext(), "کد ذخیره شد");
							}
						}else {
							L.t(MyApplication.getAppContext(), "کد ذخیره نشد !!!");
							L.t(MyApplication.getAppContext(), "گنجایش کد پر است.");
						}
					}
					//////////////////////
				} else {
					L.t(MyApplication.getAppContext(), "کد ذخیره نشد !!!");
					L.t(MyApplication.getAppContext(), " از فرستنده درخواست کنید دوباره کد را بفرستد.");
				}
				MyApplication.getWritableDatabase().del_getTable_CodeReCivide();
				//ShortcutBadger.applyCount(MyApplication.getAppContext(), parseNewMsgCount()+1);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}


	class TaskCompailSMS extends AsyncTask<Void, Void, Boolean> {
		String sms;
		String num;
		String name;

		public TaskCompailSMS(String sms,String num,String name) {
			this.sms = sms;
			this.num = num;
			this.name = name;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Boolean doInBackground(Void... params) {

			CompailSMS(sms,num,name);

			return true;
		}


		@Override
		protected void onPostExecute(Boolean res) {

		}
	}
	public static void send(){
		Intent intent=new Intent("refget");
		intent.setAction("refget");
		MyApplication.getAppContext().sendBroadcast(intent);
	}
	public static int parseNewMsg(String num){
		int cn=0;
		Cursor resultmsg=MyApplication.getWritableDatabase().getTableOfContact();
		resultmsg.move(0);
		String id="";
		while(resultmsg.moveToNext())
		{
			if(resultmsg.getString(1).equals(num)) {
				cn+=resultmsg.getInt(3);
				id=resultmsg.getString(0);
			}

		}
		resultmsg.close();
		cn=cn+1;
		MyApplication.getWritableDatabase().updateTableOfContact(id,"","",""+cn,"");
		return cn;
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
	static public String[] getTable_CodeReCivide(){
		String[] c=new String[4];
		Cursor result = MyApplication.getWritableDatabase().getTable_CodeReCivide();
		result.move(0);
		while (result.moveToNext()) {

			c[0]=result.getString(1);
			c[1]=result.getString(2);
			c[2]=result.getString(3);
			c[3]=result.getString(4);
		}
        result.close();
		return c;
	}
	static public boolean isCountactExist(String Code,String Phone) {

		boolean is=false;

		String c0="",c1="";

		Cursor result = MyApplication.getWritableDatabase().getTableOfCodeContac(Code);
		result.move(0);
		while (result.moveToNext()) {
			c0=Phone;
			c1=result.getString(2);
			if(c0.equals(c1))
			{
				is=true;
			}
		}
		result.close();
		return is;
	}
	static public boolean isCodeExist(String Code) {
		CreateChars chs=new CreateChars();
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
	static public String DeCodeSMSByContactCode(String sms,String Phone){

		String Code="";
		CreateDeCrypter deCodesms =new CreateDeCrypter();
		Cursor result = MyApplication.getWritableDatabase().getTableOfCodeContacToInDeCrypt(Phone);
		result.move(0);
		while (result.moveToNext()) {
			Code=result.getString(1);
		}

		result.close();
		if(Code.equals("") || Code.equals(null)) {

		}else {
			sms = deCodesms.DeCodeSms(sms, Code);
		}

		return sms;
	}
	public void deleteSMS(Context context, String message, String number) {
		try {
			Uri uriSms = Uri.parse("content://sms/inbox");
			Cursor c = context.getContentResolver().query(
					uriSms,
					new String[] { "_id", "thread_id", "address", "person",
							"date", "body" }, "read=0", null, null);

			if (c != null && c.moveToFirst()) {
				do {
					long id = c.getLong(0);
					long threadId = c.getLong(1);
					String address = c.getString(2);
					String body = c.getString(5);
					String date = c.getString(3);
					Log.e("log>>>",
							"0>" + c.getString(0) + "1>" + c.getString(1)
									+ "2>" + c.getString(2) + "<-1>"
									+ c.getString(3) + "4>" + c.getString(4)
									+ "5>" + c.getString(5));
					Log.e("log>>>", "date" + c.getString(0));

					if (message.equals(body) && address.equals(number)) {
						// mLogger.logInfo("Deleting SMS with id: " + threadId);
						context.getContentResolver().delete(
								Uri.parse("content://sms/" + id), "date=?",
								new String[] { c.getString(4) });
						Log.e("log>>>", "Delete success.........");
					}
				} while (c.moveToNext());
			}
		} catch (Exception e) {
			Log.e("log>>>", e.toString());
		}
	}
}
