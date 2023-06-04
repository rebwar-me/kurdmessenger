package com.rai.kurdmessenger.Utils;

import android.database.Cursor;

import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.recycler.CodeContacModel;
import com.rai.kurdmessenger.recycler.CodeModel;

import java.util.ArrayList;

/**
 * Created Created by @rebwar_me.
 */

public class CodeListUtil {

    public static ArrayList<CodeContacModel> loadCodeContactList(String Code) {

        ArrayList<CodeContacModel> Codes = new ArrayList<>();
        Cursor result = MyApplication.getWritableDatabase().getTableOfCodeContac(Code);
        result.move(0);
        while (result.moveToNext()) {
            CodeContacModel code = new CodeContacModel();
            code.setId(result.getString(0));
            code.setCode(result.getString(1));
            code.setPhone(result.getString(2));
            code.setConname(result.getString(3));
            code.setpic(getUpic(result.getString(2),result.getString(3)));
            Codes.add(code);
        }
        result.close();
        return Codes;
    }
    public static ArrayList<CodeModel> loadCodeList() {

        ArrayList<CodeModel> Codes = new ArrayList<>();
        Cursor result = MyApplication.getWritableDatabase().getTableOfCode();
        result.move(0);
        while (result.moveToNext()) {
            CodeModel code = new CodeModel();
            code.setId(result.getString(0));
            code.setCode(result.getString(1));
            code.setPhone("0");
            code.setConname("0");
            Codes.add(code);
        }
        result.close();
        return Codes;
    }
    public static String getUpic(String phone,String name){
        Cursor result = MyApplication.getWritableDatabase().getTableOfContact();
        String pic="0";
        result.move(0);
        while (result.moveToNext()) {

            if((phone.equals(result.getString(1)))&& (name.equals(result.getString(2)))) {
                pic=result.getString(4);
                break;
            }
        }
        result.close();
        return pic;
    }
}
