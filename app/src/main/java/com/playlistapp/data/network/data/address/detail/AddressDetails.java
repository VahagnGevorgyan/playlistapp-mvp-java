package com.playlistapp.data.network.data.address.detail;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressDetails implements Parcelable {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("value")
    @Expose
    private String value;

    public AddressDetails () {}

    protected AddressDetails(Parcel in) {
        type = in.readString();
        title = in.readString();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(title);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddressDetails> CREATOR = new Creator<AddressDetails>() {
        @Override
        public AddressDetails createFromParcel(Parcel in) {
            return new AddressDetails(in);
        }

        @Override
        public AddressDetails[] newArray(int size) {
            return new AddressDetails[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}