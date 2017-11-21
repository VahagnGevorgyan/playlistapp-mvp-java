package com.playlistapp.data.network.data.address;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddressData implements Parcelable {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("userid")
    @Expose
    private int userid;
    @SerializedName("addresses")
    @Expose
    private List<AddressItem> addresses;

    public AddressData() {}

    protected AddressData(Parcel in) {
        token = in.readString();
        userid = in.readInt();
        addresses = in.createTypedArrayList(AddressItem.CREATOR);
    }

    public static final Creator<AddressData> CREATOR = new Creator<AddressData>() {
        @Override
        public AddressData createFromParcel(Parcel in) {
            return new AddressData(in);
        }

        @Override
        public AddressData[] newArray(int size) {
            return new AddressData[size];
        }
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public List<AddressItem> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressItem> addresses) {
        this.addresses = addresses;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        dest.writeInt(userid);
        dest.writeTypedList(addresses);
    }
}
