package com.playlistapp.data.network.data.error;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * ApiHelper for generic error.
 */
public class ErrorData implements Serializable {

    @SerializedName("setLegal")
    String mIsLegalSet;
    @SerializedName("setParentalControl")
    String mIsParentalControlEnabled;
    @SerializedName("setPurchasePin")
    String mIsPurchasePinSet;

    public String getIsLegalSet() {
        return mIsLegalSet;
    }

    public String getIsPurchasePinSet() {
        return mIsPurchasePinSet;
    }

    public String getIsParentalControlEnabled() {
        return mIsParentalControlEnabled;
    }

    @Override
    public String toString() {
        return "ErrorData{" +
                "mIsLegalSet='" + mIsLegalSet + '\'' +
                ", mIsParentalControlEnabled='" + mIsParentalControlEnabled + '\'' +
                ", mIsPurchasePinSet='" + mIsPurchasePinSet + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrorData errorData = (ErrorData) o;

        if (mIsLegalSet != null ? !mIsLegalSet.equals(errorData.mIsLegalSet) : errorData.mIsLegalSet != null)
            return false;
        if (mIsParentalControlEnabled != null ? !mIsParentalControlEnabled.equals(errorData.mIsParentalControlEnabled) : errorData.mIsParentalControlEnabled != null)
            return false;
        return mIsPurchasePinSet != null ? mIsPurchasePinSet.equals(errorData.mIsPurchasePinSet) : errorData.mIsPurchasePinSet == null;

    }

    @Override
    public int hashCode() {
        int result = mIsLegalSet != null ? mIsLegalSet.hashCode() : 0;
        result = 31 * result + (mIsParentalControlEnabled != null ? mIsParentalControlEnabled.hashCode() : 0);
        result = 31 * result + (mIsPurchasePinSet != null ? mIsPurchasePinSet.hashCode() : 0);
        return result;
    }

}
