package com.rai.kurdmessenger.recycler;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created Created by @rebwar_me.
 */

public class CodeContacModel  implements Parcelable {
    public static final Parcelable.Creator<CodeContacModel> CREATOR
            = new Parcelable.Creator<CodeContacModel>() {
        public CodeContacModel createFromParcel(Parcel in) {
            return new CodeContacModel(in);
        }

        public CodeContacModel[] newArray(int size) {
            return new CodeContacModel[size];
        }
    };
    private String id;
    private String code;
    private String phone;
    private String conname;
    private String pic;

    public CodeContacModel() {

    }

    public CodeContacModel(Parcel input) {
        id = input.readString();
        code = input.readString();
        phone = input.readString();
        conname = input.readString();
        pic = input.readString();
    }

    public CodeContacModel(
            String id,
            String code,
            String phone,
            String conname,
            String pic
    ) {
        this.id = id;
        this.code = code;
        this.phone = phone;
        this.conname = conname;
        this.pic = pic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConname() {
        return conname;
    }

    public void setConname(String conname) {
        this.conname = conname;
    }

    public String getpic() {
        return pic;
    }

    public void setpic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nname: " + code +
                "\nnum " + phone +
                "\npic " + conname+
                "\npic " + pic
                + "\n";
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
        dest.writeString(code);
        dest.writeString(phone);
        dest.writeString(conname);
        dest.writeString(pic);

    }
}
