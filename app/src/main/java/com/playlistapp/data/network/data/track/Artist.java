package com.playlistapp.data.network.data.track;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("mbid")
    @Expose
    private String mBid;
    @SerializedName("url")
    @Expose
    private String mUrl;

    public String getName() {
        return mName;
    }

    public String getBid() {
        return mBid;
    }

    public String getUrl() {
        return mUrl;
    }
}
