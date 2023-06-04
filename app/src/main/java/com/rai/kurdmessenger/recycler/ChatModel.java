package com.rai.kurdmessenger.recycler;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alex on 01/01/2009.
 */

public class ChatModel implements Parcelable {
    public static final Parcelable.Creator<ChatModel> CREATOR
            = new Creator<ChatModel>() {
        public ChatModel createFromParcel(Parcel in) {
            return new ChatModel(in);
        }

        public ChatModel[] newArray(int size) {
            return new ChatModel[size];
        }
    };
    private String id;
    private String name;
    private String num;
    private String pic;
    private String nummsg;
    private String newmsg;

    public ChatModel() {

    }

    public ChatModel(Parcel input) {
        id= input.readString();
        name= input.readString();
        num= input.readString();
        pic= input.readString();
        nummsg= input.readString();
        newmsg= input.readString();
    }

    public ChatModel(
            String id,
            String name,
            String num,
            String pic,
            String nummsg,
            String newmsg
    ) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.pic = pic;
        this.nummsg = nummsg;
        this.newmsg = newmsg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getNummsg() {
        return nummsg;
    }

    public void setNummsg(String nummsg) {
        this.nummsg = nummsg;
    }
    public String getNewmsg() {
        return newmsg;
    }

    public void setNewmsg(String newmsg) {
        this.newmsg = newmsg;
    }

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nname: " + name +
                "\nnum " + num +
                "\npic " + pic +
                "\nnummsg " + nummsg+
                "\nnewmsg " + newmsg
                +"\n";
    }

    @Override
    public int describeContents() {
//        L.m("describe Contents Movie");
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        L.m("writeToParcel Movie");
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(num);
        dest.writeString(pic);
        dest.writeString(nummsg);
        dest.writeString(newmsg);

    }
}


