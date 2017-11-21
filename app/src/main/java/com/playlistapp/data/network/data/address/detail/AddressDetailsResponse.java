package com.playlistapp.data.network.data.address.detail;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playlistapp.data.network.data.ApiResponse;

public class AddressDetailsResponse extends ApiResponse implements Parcelable {

    @SerializedName("data")
    @Expose
    private AddressDetailsData data;

    public AddressDetailsResponse() {}

    protected AddressDetailsResponse(Parcel in) {
        data = in.readParcelable(AddressDetailsData.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddressDetailsResponse> CREATOR = new Creator<AddressDetailsResponse>() {
        @Override
        public AddressDetailsResponse createFromParcel(Parcel in) {
            return new AddressDetailsResponse(in);
        }

        @Override
        public AddressDetailsResponse[] newArray(int size) {
            return new AddressDetailsResponse[size];
        }
    };

    public AddressDetailsData getData() {
        return data;
    }

    public void setData(AddressDetailsData data) {
        this.data = data;
    }
}
