package com.playlistapp.data.network.data.track;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackResData {

    @SerializedName("@attr")
    @Expose
    private Attributes mAttributes;

    /**
     *
     * @return
     * The data
     */
    public Attributes getData() {
        return mAttributes;
    }

}
