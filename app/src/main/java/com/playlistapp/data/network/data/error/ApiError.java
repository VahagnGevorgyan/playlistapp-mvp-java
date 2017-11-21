package com.playlistapp.data.network.data.error;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Generic error for 'KO' response.
 */
public class ApiError extends Exception {

    @SerializedName("message")
    @Expose
    protected String mMessage;
    @SerializedName("status")
    @Expose
    private String mStatus;
    @SerializedName("error_code")
    @Expose
    protected int mErrorCode;

    @Override
    public String toString() {
        return "Error{" +
                "mMessage='" + mMessage + '\'' +
                ", mStatus='" + mStatus + '\'' +
                ", mErrorCode='" + mErrorCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiError error = (ApiError) o;

        return mMessage != null ? mMessage.equals(error.mMessage) : error.mMessage == null;
    }

    @Override
    public int hashCode() {
        return mMessage != null ? mMessage.hashCode() : 0;
    }
}
