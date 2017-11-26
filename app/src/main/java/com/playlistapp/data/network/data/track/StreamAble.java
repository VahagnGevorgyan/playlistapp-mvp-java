package com.playlistapp.data.network.data.track;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StreamAble {

    @SerializedName("#text")
    @Expose
    private String mText;
    @SerializedName("fulltrack")
    @Expose
    private String mFullTrack;

    public String getText() {
        return mText;
    }

    public String getFullTrack() {
        return mFullTrack;
    }
}
