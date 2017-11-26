package com.playlistapp.data.network.data.track;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackResData {

    @SerializedName("track")
    @Expose
    private List<TrackItem> mTrackItems = null;
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

    /**
     *
     * @return
     * The data
     */
    public List<TrackItem> getTrackItems() {
        return mTrackItems;
    }
}
