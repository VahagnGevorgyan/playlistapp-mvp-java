package com.playlistapp.data.network.data.address;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playlistapp.data.network.data.ApiResponse;

public class AddressResponse extends ApiResponse implements Parcelable {

    @SerializedName("data")
    @Expose
    private AddressData data;

    public AddressResponse() {}

    protected AddressResponse(Parcel in) {
        data = in.readParcelable(AddressData.class.getClassLoader());
    }

    public static final Creator<AddressResponse> CREATOR = new Creator<AddressResponse>() {
        @Override
        public AddressResponse createFromParcel(Parcel in) {
            return new AddressResponse(in);
        }

        @Override
        public AddressResponse[] newArray(int size) {
            return new AddressResponse[size];
        }
    };

    public AddressData getData() {
        return data;
    }

    public void setData(AddressData data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(data, flags);
    }
}
