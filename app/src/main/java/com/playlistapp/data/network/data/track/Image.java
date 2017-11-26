package com.playlistapp.data.network.data.track;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {
    @SerializedName("#text")
    @Expose
    private String mText;
    @SerializedName("size")
    @Expose
    private String mSize;

    public String getText() {
        return mText;
    }

    public String getSize() {
        return mSize;
    }
}
