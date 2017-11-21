package com.playlistapp.data.network.data.city;


import com.google.gson.annotations.SerializedName;
import com.playlistapp.data.network.data.ApiResponse;

import java.util.List;

public class CityListApiResponse extends ApiResponse {

    @SerializedName("data")
    private List<CityItem> mData = null;

    public List<CityItem> getData() {
        return mData;
    }

    public void setData(List<CityItem> data) {
        this.mData = data;
    }

    @Override
    public String toString() {
        return "CityListResultObject \n{ " +
                "data = \n" + mData +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CityListApiResponse that = (CityListApiResponse) o;

        return mData != null ? mData.equals(that.mData) : that.mData == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mData != null ? mData.hashCode() : 0);
        return result;
    }
}
