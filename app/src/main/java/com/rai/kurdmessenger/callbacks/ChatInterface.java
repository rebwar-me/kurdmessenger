package com.rai.kurdmessenger.callbacks;

import com.rai.kurdmessenger.recycler.ChatModel;

import java.util.ArrayList;

/**
 * Created Created by @rebwar_me.
 */

public interface ChatInterface {
    public void onChatListLoaded(ArrayList<ChatModel> chats);
}
