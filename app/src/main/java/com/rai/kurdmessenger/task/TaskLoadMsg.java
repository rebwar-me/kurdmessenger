package com.rai.kurdmessenger.task;

import android.os.AsyncTask;

import com.rai.kurdmessenger.Utils.MsgListUtil;
import com.rai.kurdmessenger.callbacks.MsgInterface;
import com.rai.kurdmessenger.recycler.MsgModel;

import java.util.ArrayList;

public class TaskLoadMsg extends AsyncTask<Void, Void, ArrayList<MsgModel>> {
    private MsgInterface myComponent;
    private String number;
    public TaskLoadMsg(MsgInterface myComponent,String number) {
        this.myComponent = myComponent;
        this.number = number;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ArrayList<MsgModel> doInBackground(Void... params) {

        ArrayList<MsgModel> ConList = MsgListUtil.loadMsgs(number);

        return ConList;
    }


    @Override
    protected void onPostExecute(ArrayList<MsgModel> ConList) {
        if (myComponent != null) {
            myComponent.onMsgListLoaded(ConList);
        }
    }
}