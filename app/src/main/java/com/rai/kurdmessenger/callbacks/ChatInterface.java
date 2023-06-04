package com.rai.kurdmessenger.callbacks;

import com.rai.kurdmessenger.recycler.ChatModel;

import java.util.ArrayList;

/**
 * Created by alex on 01/01/2009.
 */

public interface ChatInterface {
    public void onChatListLoaded(ArrayList<ChatModel> chats);
}
