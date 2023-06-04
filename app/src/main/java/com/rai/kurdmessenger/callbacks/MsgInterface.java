package com.rai.kurdmessenger.callbacks;

import com.rai.kurdmessenger.recycler.MsgModel;

import java.util.ArrayList;

public interface MsgInterface {
    public void onMsgListLoaded(ArrayList<MsgModel> msgs);
}
