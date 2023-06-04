package com.rai.kurdmessenger.recycler;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created Created by @rebwar_me.
 */

public class CodeModel implements Parcelable {
    public static final Parcelable.Creator<CodeModel> CREATOR
            = new Creator<CodeModel>() {
        public CodeModel createFromParcel(Parcel in) {
            return new CodeModel(in);
        }

        public CodeModel[] newArray(int size) {
            return new CodeModel[size];
        }
    };
    private String id;
    private String code;
    private String phone;
    private String conname;

    public CodeModel() {

    }

    public CodeModel(Parcel input) {
        id= input.readString();
        code= input.readString();
        phone= input.readString();
        conname= input.readString();
    }

    public CodeModel(
            String id,
            String code,
            String phone,
            String conname
    ) {
        this.id = id;
        this.code = code;
        this.phone = phone;
        this.conname = conname;
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

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nname: " + code +
                "\nnum " + phone +
                "\npic " + conname
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
        dest.writeString(code);
        dest.writeString(phone);
        dest.writeString(conname);

    }
}