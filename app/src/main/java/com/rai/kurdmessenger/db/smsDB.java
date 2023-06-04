package com.rai.kurdmessenger.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.log.L;

import java.util.HashMap;

/**
 * Created by alex on 01/01/2009.
 */

public class smsDB {

        private static String DB_NAME = "sms.db";
        private static int DB_VERSION = 1;
        public static final String CO_ID = "id";
        public static final String MSG = "msg";
        public static final String NUM = "num";
        public static final String NAME = "name";
        public static final String TIME = "time";
        public static final String HOW = "how";
        public static final String REC = "rec";
        public static final String NEW = "new";
        public static final String DEL = "del";
        public static final String RMZ = "rmz";
        public static final String CODE = "code";
        public static final String DECODE = "decode";
        public static final String SENDER = "sender";
        public static final String FLAG = "flag";
    public static final String UPIC = "upic";



    public static final String TABLE_SMS = "smstbl";
    public static final String TABLE_SMS_WILL_BE_SEND = "wsms";
    public static final String TABLE_SMS_SENDING = "smsing";
    public static final String TABLE_CodeReCivide = "coderecived";
    public static final String TABLE_DEL_FLAG = "delflag";
    public static final String TABLE_CONTACT = "contbl";
    public static final String TABLE_RAMZ= "ramz";
    public static final String TABLE_CODE_CONTAC= "tblcodecontact";
    public static final String TABLE_CODE = "code";
    public static final String PREMISSION = "premission";




        private dbHelper mHelper;
        private SQLiteDatabase db;

        public smsDB(Context context)
        {
            mHelper=new dbHelper(context);
            db=mHelper.getWritableDatabase();
        }
    public int CodeConter()
    {
        int count=0;
        Cursor result = db.rawQuery( "SELECT Count(*) FROM " + TABLE_CODE, null );
        result.move(0);

        if(result.getCount()>0)
        {
            result.moveToFirst();
            count=result.getInt(0);
        }
        result.close();

       return count;
    }
    public int CodeCountactConter(String Code)
    {
        int count=0;
        Cursor result = db.rawQuery( "SELECT Count(*) FROM " + TABLE_CODE_CONTAC + " WHERE code='"+Code+"';", null );
        result.move(0);

        if(result.getCount()>0)
        {
            result.moveToFirst();
            count=result.getInt(0);
        }
        result.close();

        return count;
    }
    public  Cursor getTableOfCodeContac(String Code)
    {
        Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_CODE_CONTAC + " WHERE code='"+Code+"';", null );
        result.move(0);

        return result;
    }
    public  Cursor getTable_CodeReCivide()
    {
        Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_CodeReCivide , null );
        result.move(0);

        return result;
    }
    public boolean setTable_CodeReCivide(int id, String code, String decode,String sender,String name )
    {
//        try {
//
//        }catch (Exception e){
//            e.printStackTrace();
//            return false;
//        }
        ContentValues cv = new ContentValues();
        // cv.put("id","1");
        if(!code.isEmpty()) {
            cv.put(CODE, code);
        }
        if(!decode.isEmpty()) {
            cv.put(DECODE, decode);
        }
        if(!sender.isEmpty()) {
            cv.put(SENDER, sender);
        }
        if(name != null) {
            if(!name.isEmpty()){
                cv.put(NAME, name);
            }else {
                cv.put(NAME,(sender+""));
            }
        }else {
            cv.put(NAME,(sender+""));
        }
        long result = db.insert(TABLE_CodeReCivide,null,cv);
        if( result < 1 )
            return false;
        else
            return true;
    }
    public  Cursor getTableOfCodeContacToInDeCrypt(String phone)
    {
        Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_CODE_CONTAC + " WHERE  num='"+phone+"';", null );
        result.move(0);

        return result;
    }
    public  Cursor getTableOfCode()
    {
        Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_CODE, null );
        result.move(0);
        return result;
    }

    public boolean setTableOfCodeContact(int id, String code, String num, String name)
    {
        ContentValues cv = new ContentValues();

        try {
            // cv.put("id","1");
            if(!code.isEmpty()) {
                cv.put(CODE, code);
            }
            if(!num.isEmpty()) {
                cv.put(NUM, num);
            }
            if(!name.isEmpty()) {
                cv.put(NAME,name);
            }
            long result = db.insert(TABLE_CODE_CONTAC,null,cv);
            if( result < 1 )
                return false;
            else
                return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean setTableOfCode(int id, String code)
    {
        ContentValues cv = new ContentValues();


        try {
            // cv.put("id","1");
            if(!code.isEmpty()) {
                cv.put(CODE, code);
            }
            long result = db.insert(TABLE_CODE,null,cv);
            if( result < 1 )
                return false;
            else
                return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    /**
     * SEND SMS
     */

    public int WillBeSendConter()
    {
        int count=0;
        Cursor result = db.rawQuery( "SELECT Count(*) FROM " + TABLE_SMS_WILL_BE_SEND , null );
        result.move(0);

        if(result.getCount()>0)
        {
            result.moveToFirst();
            count=result.getInt(0);
        }
        result.close();

        return count;
    }
    public int SendingConter()
    {
        int count=0;
        Cursor result = db.rawQuery( "SELECT Count(*) FROM " + TABLE_SMS_SENDING , null );
        result.move(0);

        if(result.getCount()>0)
        {
            result.moveToFirst();
            count=result.getInt(0);
        }
        result.close();

        return count;
    }
    public  Cursor getTableOfMsgWillBeSend()
    {
        Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_SMS_WILL_BE_SEND  , null );
        result.move(0);

        return result;
    }
    public boolean setTableOfMsgWillBeSend(int id,String msg,String num,String name)
    {
        ContentValues cv = new ContentValues();
        // cv.put("id","1");
        if(!msg.isEmpty()) {
            cv.put(MSG, msg);
        }
        if(!num.isEmpty()) {
            cv.put(NUM, num);
        }
        if(!name.isEmpty()) {
            cv.put(NAME,name);
        }
        long result = db.insert(TABLE_SMS_WILL_BE_SEND ,null,cv);
        if( result < 1 )
            return false;
        else
            return true;
    }
    public  Cursor getTableOfMsgSending()
    {
        Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_SMS_SENDING  , null );
        result.move(0);

        return result;
    }
    public boolean setTableOfMsgSending(int id,String msg,String num,String name)
    {
        ContentValues cv = new ContentValues();
        // cv.put("id","1");
        if(!msg.isEmpty()) {
            cv.put(MSG, msg);
        }
        if(!num.isEmpty()) {
            cv.put(NUM, num);
        }
        if(!name.isEmpty()) {
            cv.put(NAME,name);
        }
        long result = db.insert(TABLE_SMS_SENDING ,null,cv);
        if( result < 1 )
            return false;
        else
            return true;
    }


    /**
     * * SEND SMS |^|
     */

    ///////////////////////////////////////////
    /**
     * S    M    S
     */
    public  Cursor getTableOfMsg()
        {
            Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_SMS  , null );
            result.move(0);

            return result;
        }

    public boolean updatemsg(String id,String msg,String num,String name,String time,String how,String rec )
    {


        try {
            ContentValues cv = new ContentValues();

            if(!msg.isEmpty()) {
                cv.put(MSG, msg);
            }
            if(!num.isEmpty()) {
                cv.put(NUM, num);
            }
            if(!name.isEmpty()) {
                cv.put(NAME,name);
            }
            if(!time.isEmpty()) {
                cv.put(TIME,time);
            }
            if(!how.isEmpty()) {
                cv.put(HOW,how);
            }
            if(!rec.isEmpty()) {
                cv.put(REC,rec);
            }

            long result = db.update(TABLE_SMS ,cv, "id = ?" ,new String[] {id});
            if( result < 1 )
                return false;
            else
                return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
        public boolean setTableOfMsg(int id,String msg,String num,String name,String time,String how,String rec )
        {
            try {
                ContentValues cv = new ContentValues();
                // cv.put("id","1");
                if(!msg.isEmpty()) {
                    cv.put(MSG, msg);
                }
                if(!num.isEmpty()) {
                    cv.put(NUM, num);
                }
                if(!name.isEmpty()) {
                    cv.put(NAME,name);
                }
                if(!time.isEmpty()) {
                    cv.put(TIME,time);
                }
                if(!how.isEmpty()) {
                    cv.put(HOW,how);
                }
                if(!name.isEmpty()) {
                    cv.put(REC,rec);
                }
                long result = db.insert(TABLE_SMS ,null,cv);
                if( result < 1 )
                    return false;
                else
                    return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }


        }
        //////////////////////////


        public int contAllMsg()
        {
            Cursor cont = db.rawQuery( "SELECT * FROM " + TABLE_SMS ,null);
            cont.move(0);

            return cont.getCount();
        }


        //////////////////////////Createbc&&savedBC//////////////////
//        public void deleteAllmsgfromCon(String name) {
//
//
//           // return db.delete(TABLE_CONTACT,null,null);
//        }
        public String getDelFlag()
        {
            Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_DEL_FLAG  , null );
            result.move(0);

            String flag="0";

            while( result.moveToNext() )
            {
                flag=result.getString(1);


            }
            result.close();
            return flag;
        }
        public Integer del_flag() {
            return db.delete(TABLE_DEL_FLAG,null,null);
        }

    public Integer del_getTable_CodeReCivide() {

        return db.delete(TABLE_CodeReCivide,null,null);
    }

    public boolean setDelFlag(String flag)
    {
        ContentValues cv = new ContentValues();
        if(!flag.isEmpty()) {
            cv.put(DEL,flag);
        }
        long result = db.insert(TABLE_DEL_FLAG ,null,cv);
        if( result < 1 )
            return false;
        else
            return true;
    }

    /**
    *__________________________
     */
    public boolean setPremissoin(String flag)
    {

        try {
            ContentValues cv = new ContentValues();
            if(!flag.isEmpty()) {
                cv.put(FLAG,flag);
            }
            long result = db.insert(PREMISSION ,null,cv);
            if( result < 1 )
                return false;
            else
                return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public String getPremissoin()
    {
        Cursor result = db.rawQuery( "SELECT * FROM " + PREMISSION  , null );
        result.move(0);

        String flag="0";

        while( result.moveToNext() )
        {
            flag=result.getString(1);

        }
        if(flag.equals(null)){
            flag="0";
        }
        result.close();
        return flag;
    }
    /**
     *__________________________
     */
/////////////////////////////////////Delets
     public Integer DeleteChat(String id,String name,String num) {
        if(deleteCon(id)>0) {
            return DeleteAllMsg(name,num);
        }else {
            return deleteCon(id);
        }
    }
    public Integer DeleteAllMsg(String name,String num) {
        return db.delete(TABLE_SMS, "name = ? AND num=?", new String[]{name,num});
    }
    public Integer deleteCon(String id ) {
        return db.delete(TABLE_CONTACT, "id = ?", new String[]{id});
    }

    public Integer deleteMsg(String id) {
            return db.delete(TABLE_SMS, "id = ?", new String[]{id});

        }
    public Integer deleteCode(String Code) {
        Integer re=0;
        MyApplication.getWritableDatabase().deleteCodeContact(Code);
        re = db.delete(TABLE_CODE, "code = ?", new String[]{Code});
        return re;
    }
    public Integer deleteCodeContact(String Code) {
        return db.delete(TABLE_CODE_CONTAC, "code = ?", new String[]{Code});
    }
    public Integer deleteContactByCode(String id) {
        return db.delete(TABLE_CODE_CONTAC, "id = ?", new String[]{id});
    }
    public Integer deleteContactByCodeToAddHemToNewCode(String phone) {
        return db.delete(TABLE_CODE_CONTAC, "num = ?", new String[]{phone});
    }
            ///////////////////////////////////////////
            public Cursor getTableOfContact()
            {
                Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_CONTACT  , null );
                return result;

            }
    public boolean updateTableOfContact(String id,String num,String name,String newmsg,String upic)
    {
        ContentValues cv = new ContentValues();

        if(!num.isEmpty()) {
            cv.put(NUM, num);
        }
        if(!name.isEmpty()) {
            cv.put(NAME,name);
        }
        if(!newmsg.isEmpty()) {
            cv.put(NEW,newmsg);
        }
        if(!upic.isEmpty()) {
            cv.put(UPIC,upic);
        }
        long result = db.update(TABLE_CONTACT ,cv, "id = ?" ,new String[] {id});
        if( result < 1 )
            return false;
        else
            return true;

    }
    public HashMap<String , Object> getTableOfCon()
    {
        Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_CONTACT  , null );
        result.move(0);

        HashMap<String , Object> all_data = new HashMap<>();

        while( result.moveToNext() )
        {
            all_data.put(CO_ID,1);
            all_data.put( NUM , result.getString(1));
            all_data.put(NAME, result.getString(2));
            all_data.put(NEW, result.getString(3));
            all_data.put(UPIC, result.getString(4));
        }
        return all_data;
    }
    public boolean chckTableOfCon(String name,String num)
    {
        Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_CONTACT  , null );
        result.move(0);

        HashMap<String , Object> all_data = new HashMap<>();
        boolean flag=false;

        while( result.moveToNext() )
        {
            if(result.getString(1).equals(num)){
                flag=true;
            }

        }
        return flag;
    }

    public boolean setTableOfCon(int id,String num,String name,String newmsg,String Upic)
    {


        try {

            ContentValues cv = new ContentValues();
            // cv.put("id","1");

            if(!num.isEmpty()) {
                cv.put(NUM, num);
            }
            if(!name.isEmpty()) {
                cv.put(NAME,name);
            }
            if(!newmsg.isEmpty()) {
                cv.put(NEW,newmsg);
            }
            if(!Upic.isEmpty()) {
                cv.put(UPIC,Upic);
            }
            long result =0;
            if(chckTableOfCon(name,num)==false) {
                result = db.insert(TABLE_CONTACT ,null,cv);
            }
            if( result < 1 )
                return false;
            else
                return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public String getRmz(){
        Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_RAMZ  , null );
        result.move(0);

        String rmz="";

        while( result.moveToNext() )
        {
            rmz=result.getString(1);
        }
        return rmz;
    }
    public String getRmzf(){
        Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_RAMZ  , null );
        result.move(0);

        String rmz="";

        while( result.moveToNext() )
        {
            rmz=result.getString(2);
        }
        return rmz;
    }

  public boolean setRmz(){
     try {
         ContentValues cv = new ContentValues();
         cv.put("id",1);
         cv.put(RMZ,"00");
         cv.put(FLAG,"0");
         long result = db.insert(TABLE_RAMZ ,null,cv);
         if( result < 1 )
             return false;
         else
             return true;
     }catch (Exception e){
         e.printStackTrace();
         return false;
     }
  }
  public boolean updateRmz(String r){


        try {

            ContentValues cv = new ContentValues();

            cv.put(RMZ,r);
            long result = db.update(TABLE_RAMZ ,cv, "id ="+1 ,null);
            if( result < 1 )
                return false;
            else
                return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public boolean updateRmzf(String r){


        try {
            ContentValues cv = new ContentValues();

            cv.put(FLAG,r);
            long result = db.update(TABLE_RAMZ ,cv, "id ="+1 ,null);
            if( result < 1 )
                return false;
            else
                return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }
//        public HashMap<String , Object> getTableOfMsgByNum(String num)
//        {
//            Cursor result = db.rawQuery( "SELECT * FROM " + TABLE_SMS +" WHERE num="+num , null );
//
//            HashMap<String , Object> all_data = new HashMap<>();
//
//            while( result.moveToNext() )
//            {
//                all_data.put(CO_ID,result.getString(0));
//                all_data.put(MSG, result.getString(1));
//                all_data.put(NUM , result.getString(2));
//                all_data.put(NAME, result.getString(3));
//                all_data.put(REC, result.getString(4));
//                L.t(MyApplication.getAppContext(),""+result.getString(3)+"\n"+result.getString(2)+"\n"+result.getString(1));
//            }
//            return all_data;
//        }



        private static class dbHelper extends SQLiteOpenHelper {

            public Context mcContext;


            private static final String CREATE_SMS_TBL = "CREATE TABLE " + TABLE_SMS + " ( " +
                    CO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MSG + " TEXT," +
                    NUM + " TEXT," +
                    NAME + " TEXT," +
                    TIME + " TEXT," +
                    HOW + " TEXT," +
                    REC + " TEXT" +
                    " )";
            ///////////////////////////////
            private static final String CREATE_TABLE_SMS_WILL_BE_SEND = "CREATE TABLE " + TABLE_SMS_WILL_BE_SEND + " ( " +
                    CO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MSG + " TEXT," +
                    NUM + " TEXT," +
                    NAME + " TEXT" +
                    " )";
            ///////////////////////////////
            private static final String CREATE_TABLE_SMS_SENDING = "CREATE TABLE " + TABLE_SMS_SENDING + " ( " +
                    CO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    MSG + " TEXT," +
                    NUM + " TEXT," +
                    NAME + " TEXT" +
                    " )";
            //////////////////////////////
            private static final String CREATE_CONTACT_TBL = "CREATE TABLE " + TABLE_CONTACT + " ( " +
                    CO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NUM + " TEXT," +
                    NAME + " TEXT," +
                    NEW + " TEXT," +
                    UPIC+ " TEXT" +
                    " )";
            ///////////////////////////////
            private static final String CREATE_CODE_CONTACT_TBL = "CREATE TABLE " + TABLE_CODE_CONTAC + " ( " +
                    CO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CODE + " TEXT," +
                    NUM + " TEXT," +
                    NAME + " TEXT" +
                    " )";
            ///////////////////////////////
            private static final String CREATE_TABLE_DEL_FLAG = "CREATE TABLE " + TABLE_DEL_FLAG + " ( " +
                    CO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DEL + " TEXT" +
                    " )";
            private static final String CREATE_TABLE_CODERECIVIDE = "CREATE TABLE " + TABLE_CodeReCivide + " ( " +
                    CO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CODE + " TEXT," +
                    DECODE + " TEXT," +
                    SENDER + " TEXT," +
                    NAME + " TEXT" +
                    " )";
            private static final String CREATE_TABLE_CODE = "CREATE TABLE " + TABLE_CODE + " ( " +
                    CO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CODE + " TEXT" +
                    " )";
            //////////////////////////////////////
            private static final String CREATE_TABLE_RAMZ = "CREATE TABLE " + TABLE_RAMZ + " ( " +
                    CO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RMZ + " TEXT," +
                    FLAG + " TEXT" +
                    " )";
            //////////////////////////////////////
            private static final String CREATE_TABLE_PREMISSION= "CREATE TABLE " + PREMISSION + " ( " +
                    CO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FLAG + " TEXT" +
                    " )";
            ///////////////////////////////
            public dbHelper(Context ctxt) {
                super(ctxt, DB_NAME, null, 1);

                mcContext = ctxt;

            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                try {
                    db.execSQL(CREATE_SMS_TBL);
                    db.execSQL(CREATE_CONTACT_TBL);
                    db.execSQL(CREATE_TABLE_DEL_FLAG);
                    db.execSQL(CREATE_TABLE_RAMZ);
                    db.execSQL(CREATE_CODE_CONTACT_TBL);
                    db.execSQL(CREATE_TABLE_CODE);
                    db.execSQL(CREATE_TABLE_CODERECIVIDE);
                    db.execSQL(CREATE_TABLE_PREMISSION);
//                    db.execSQL(CREATE_TABLE_SMS_WILL_BE_SEND);
//                    db.execSQL(CREATE_TABLE_SMS_SENDING);
                    //L.m("create table box office executed");
                } catch (SQLiteException exception) {

                    L.t(mcContext, exception + "");
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try {
                    //L.m("upgrade table box office executed");
                    db.execSQL(" DROP TABLE " + CREATE_SMS_TBL + " IF EXISTS ;");
                    db.execSQL(" DROP TABLE " + CREATE_CONTACT_TBL + " IF EXISTS ;");
                    db.execSQL(" DROP TABLE " + CREATE_TABLE_DEL_FLAG + " IF EXISTS ;");
                    db.execSQL(" DROP TABLE " + CREATE_TABLE_RAMZ + " IF EXISTS ;");
                    db.execSQL(" DROP TABLE " + CREATE_CODE_CONTACT_TBL + " IF EXISTS ;");
                    db.execSQL(" DROP TABLE " + CREATE_TABLE_CODE + " IF EXISTS ;");
                    db.execSQL(" DROP TABLE " + CREATE_TABLE_CODERECIVIDE + " IF EXISTS ;");
                    db.execSQL(" DROP TABLE " + CREATE_TABLE_PREMISSION + " IF EXISTS ;");
//                    db.execSQL(" DROP TABLE " + CREATE_TABLE_SMS_WILL_BE_SEND + " IF EXISTS ;");
//                    db.execSQL(" DROP TABLE " + CREATE_TABLE_SMS_SENDING + " IF EXISTS ;");
                    onCreate(db);
                } catch (SQLiteException exception) {
                    L.t(mcContext, exception + "");
                }
            }

        }
    }
