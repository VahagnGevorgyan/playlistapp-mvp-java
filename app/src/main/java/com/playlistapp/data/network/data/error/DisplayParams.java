package com.playlistapp.data.network.data.error;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Display params of generic error.
 */
public class DisplayParams implements Serializable {

    @SerializedName("type")
    String mType;
    @SerializedName("title")
    String mTitle;
    @SerializedName("body")
    String mBody;

    public String getType() {
        return mType;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getBody() {
        return mBody;
    }



    @Override
    public String toString() {
        return "DisplayParams{" +
                "mType='" + mType + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mBody='" + mBody + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = mType != null ? mType.hashCode() : 0;
        result = 31 * result + (mTitle != null ? mTitle.hashCode() : 0);
        result = 31 * result + (mBody != null ? mBody.hashCode() : 0);
        return result;
    }

}
