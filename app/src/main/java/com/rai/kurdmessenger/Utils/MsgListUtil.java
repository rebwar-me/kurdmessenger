package com.rai.kurdmessenger.Utils;

import android.database.Cursor;

import com.rai.kurdmessenger.app.MyApplication;
import com.rai.kurdmessenger.recycler.MsgModel;

import java.util.ArrayList;

public class MsgListUtil {
    public static ArrayList<MsgModel> loadMsgs(String number) {

        ArrayList<MsgModel> msgs = new ArrayList<>();
        Cursor result = MyApplication.getWritableDatabase().getTableOfMsg();
        result.move(0);
        while (result.moveToNext()) {


            if(result.getString(2).equals(number)) {
                MsgModel m = new MsgModel();
                m.setId(result.getString(0));
                m.setNum(result.getString(2));
                m.setName(result.getString(3));
                m.settahvil(result.getString(6));
                m.setmsg(result.getString(1));
                m.setHow(result.getString(5));
                m.settime(result.getString(4));
                //MyApplication.getWritableDatabase().updatemsg(result.getString(0), "", "", "", "", "", "","0");
                msgs.add(m);
            }

        }
        result.close();
        return msgs;
    }
}
