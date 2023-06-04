package com.rai.kurdmessenger.task;

import android.os.AsyncTask;

import com.rai.kurdmessenger.Utils.ChatListUtil;

import com.rai.kurdmessenger.callbacks.ChatInterface;
import com.rai.kurdmessenger.recycler.ChatModel;

import java.util.ArrayList;

/**
 * Created Created by @rebwar_me.
 */

public class TaskLoadChats extends AsyncTask<Void, Void, ArrayList<ChatModel>> {
    private ChatInterface chatInterface;

    public TaskLoadChats(ChatInterface chatInterface) {
        this.chatInterface = chatInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<ChatModel> doInBackground(Void... params) {

        ArrayList<ChatModel> ConList = new ChatListUtil().loadChatList();

        return ConList;
    }


    @Override
    protected void onPostExecute(ArrayList<ChatModel> ConList) {
        if (chatInterface != null) {

            chatInterface.onChatListLoaded(ConList);
        }
    }
}

