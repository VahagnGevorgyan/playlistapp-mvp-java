package com.playlistapp.data.network.data.address.detail;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressDetailsData implements Parcelable {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("userid")
    @Expose
    private int userid;
    @SerializedName("addresses")
    @Expose
    private AddressDetailsInfo addressDetailsInfo;

    public AddressDetailsData() {}

    protected AddressDetailsData(Parcel in) {
        token = in.readString();
        userid = in.readInt();
        addressDetailsInfo = in.readParcelable(AddressDetailsInfo.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(token);
        dest.writeInt(userid);
        dest.writeParcelable(addressDetailsInfo, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddressDetailsData> CREATOR = new Creator<AddressDetailsData>() {
        @Override
        public AddressDetailsData createFromParcel(Parcel in) {
            return new AddressDetailsData(in);
        }

        @Override
        public AddressDetailsData[] newArray(int size) {
            return new AddressDetailsData[size];
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

    public AddressDetailsInfo getAddressDetailsInfo() {
        return addressDetailsInfo;
    }

    public void setAddressDetailsInfo(AddressDetailsInfo addressDetailsInfo) {
        this.addressDetailsInfo = addressDetailsInfo;
    }
}