package com.playlistapp.data.network.data.address;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressItem implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("title")
    @Expose
    private String title;

    public AddressItem() {}

    protected AddressItem(Parcel in) {
        id = in.readInt();
        flag = in.readString();
        name = in.readString();
        title = in.readString();
    }

    public static final Creator<AddressItem> CREATOR = new Creator<AddressItem>() {
        @Override
        public AddressItem createFromParcel(Parcel in) {
            return new AddressItem(in);
        }

        @Override
        public AddressItem[] newArray(int size) {
            return new AddressItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(flag);
        dest.writeString(name);
        dest.writeString(title);
    }
}