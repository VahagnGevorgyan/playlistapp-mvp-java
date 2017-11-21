package com.playlistapp.data.network.data;


import com.google.gson.annotations.SerializedName;
import com.playlistapp.data.network.data.error.ApiError;

import java.io.Serializable;

/**
 * Generic response object.
 */
public class ApiResponse extends ApiError implements Serializable {

    @SerializedName("version")
    private String mVersion;
    @SerializedName("msg")
    private String mMsg;
    @SerializedName("code")
    private int mCode;

    public String getVersion() {
        return mVersion;
    }


    public boolean isSuccessful() {
        return mErrorCode <= 0;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "mVersion='" + mVersion + '\'' +
                ", mCode=" + mCode +
                '}';
    }

    public boolean isOk() {
        return mMsg.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiResponse apiResponse = (ApiResponse) o;

        if (mMsg != null ? !mMsg.equals(apiResponse.mMsg) : apiResponse.mMsg != null)
            return false;
        return mCode == apiResponse.mCode;

    }

    @Override
    public int hashCode() {
        int result = mMsg != null ? mMsg.hashCode() : 0;
        result = 31 * result + mCode;
        return result;
    }
}
