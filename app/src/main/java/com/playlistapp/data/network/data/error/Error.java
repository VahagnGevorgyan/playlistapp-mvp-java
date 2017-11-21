package com.playlistapp.data.network.data.error;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Generic error for 'KO' response.
 */
public class Error implements Serializable {

    @SerializedName("status_message")
    @Expose
    private String mStatusMessage;
    @SerializedName("success")
    @Expose
    private boolean mSuccess;
    @SerializedName("status_code")
    @Expose
    private int mStatusCode;

    public String getStatusMessage() {
        return mStatusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.mStatusMessage = statusMessage;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public void setSuccess(boolean success) {
        this.mSuccess = success;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int statusCode) {
        this.mStatusCode = statusCode;
    }

    @Override
    public String toString() {
        return "Error{" +
                "mStatusMessage='" + mStatusMessage + '\'' +
                ", mSuccess='" + mSuccess + '\'' +
                ", mStatusCode='" + mStatusCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Error error = (Error) o;

        return mStatusMessage != null ? mStatusMessage.equals(error.mStatusMessage) : error.mStatusMessage == null;
    }

    @Override
    public int hashCode() {
        return mStatusMessage != null ? mStatusMessage.hashCode() : 0;
    }

}