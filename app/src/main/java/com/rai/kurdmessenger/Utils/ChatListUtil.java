package com.rai.kurdmessenger.Utils;

import android.database.Cursor;

import com.rai.kurdmessenger.app.MyApplication;
//import com.rai.kurdmessenger.recycler.CodeContacData;
import com.rai.kurdmessenger.log.L;
import com.rai.kurdmessenger.recycler.ChatModel;

import java.util.ArrayList;

/**
 * Created by Alex DS on 10/21/2017.
 */

public class ChatListUtil {

    public static ArrayList<ChatModel> loadChatList() {

        ArrayList<ChatModel> chats = new ArrayList<>();
        Cursor result = MyApplication.getWritableDatabase().getTableOfContact();

        result.move(0);
        int numofmsg = 0;
        while (result.moveToNext()) {
            ChatModel chat = new ChatModel();
            chat.setId(result.getString(0));
            chat.setNum(result.getString(1));
            chat.setName(result.getString(2));
            Cursor resultmsg = MyApplication.getWritableDatabase().getTableOfMsg();
            resultmsg.move(0);
            while (resultmsg.moveToNext()) {

                if (resultmsg.getString(2).equals(result.getString(1))) {
                    numofmsg = numofmsg + 1;
                }

            }
            chat.setNewmsg(result.getString(3));
            chat.setNummsg("" + numofmsg);
            chat.setPic(result.getString(4));

            chats.add(chat);
            numofmsg = 0;
            resultmsg.close();
        }
        result.close();
        return chats;
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
