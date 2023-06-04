package com.rai.kurdmessenger.recycler;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created Created by @rebwar_me.
 */

public class MsgModel implements Parcelable {
    public static final Parcelable.Creator<MsgModel> CREATOR
            = new Creator<MsgModel>() {
        public MsgModel createFromParcel(Parcel in) {
            return new MsgModel(in);
        }

        public MsgModel[] newArray(int size) {
            return new MsgModel[size];
        }
    };
    private String id;
    private String name;
    private String num;
    private String rec;
    private String msg;
    private String how;
    private String time;

    public MsgModel() {

    }

    public MsgModel(Parcel input) {
        id= input.readString();
        name= input.readString();
        num= input.readString();
        rec= input.readString();
        msg= input.readString();
        time= input.readString();
        how= input.readString();
    }

    public MsgModel(
            String id,
            String name,
            String num,
            String rec,
            String msg,
            String how,
            String time
    ) {
        this.id = id;
        this.name = name;
        this.num = num;
        this.rec = rec;
        this.msg = msg;
        this.how = how;
        this.time = time;
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



    public String gettahvil() {
        return rec;
    }
    public void settahvil(String rec) {
        this.rec = rec;
    }




    public String getmsg() {
        return msg;
    }
    public void setmsg(String msg) {
        this.msg = msg;
    }



    public String gettime() {
        return time;
    }
    public void settime(String time) {
        this.time = time;
    }




    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }


    @Override
    public String toString() {
        return "\nID: " + id +
                "\nname: " + name +
                "\nnum: " + num +
                "\nrec: " + rec +
                "\nmsg: " + msg+
                "\nhow: " + how+
                "\ntime: " + time
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
        dest.writeString(rec);
        dest.writeString(msg);
        dest.writeString(how);
        dest.writeString(time);

    }
}


