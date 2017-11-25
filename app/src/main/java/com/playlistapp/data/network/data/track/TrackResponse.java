package com.playlistapp.data.network.data.track;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playlistapp.data.network.data.ApiResponse;

public class TrackResponse extends ApiResponse {

    @SerializedName("tracks")
    @Expose
    private TrackResData mData;

    /**
     *
     * @return
     * The data
     */
    public TrackResData getData() {
        return mData;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(TrackResData data) {
        this.mData = data;
    }
}
