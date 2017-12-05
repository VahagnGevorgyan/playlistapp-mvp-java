package com.playlistapp.data.network.data.track;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackItem {

    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("duration")
    @Expose
    private String mDuration;
    @SerializedName("listeners")
    @Expose
    private String mListeners;
    @SerializedName("mbid")
    @Expose
    private String mBid;
    @SerializedName("url")
    @Expose
    private String mUrl;
    @SerializedName("streamable")
    @Expose
    private StreamAble mStreamAble;
    @SerializedName("artist")
    @Expose
    private Artist mArtist;
    @SerializedName("image")
    @Expose
    private List<Image> mImageList = null;

    private boolean mIsFavorite;

    public String getName() {
        return mName;
    }

    public String getDuration() {
        return mDuration;
    }

    public String getListeners() {
        return mListeners;
    }

    public String getBid() {
        return mBid;
    }

    public String getUrl() {
        return mUrl;
    }

    public StreamAble getStreamAble() {
        return mStreamAble;
    }

    public Artist getArtist() {
        return mArtist;
    }

    public List<Image> getImageList() {
        return mImageList;
    }

    public boolean isFavorite() {
        return mIsFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.mIsFavorite = isFavorite;
    }

    @Override
    public String toString() {
        return "\n{ " +
                "mName = " + mName +
                ", mDuration = " + mDuration +
                ", mListeners = " + mListeners +
                ", mBid = " + mBid +
                ", mStreamAble = " + mStreamAble +
                ", mArtist = " + mArtist +
                ", mImageList = " + mImageList +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TrackItem that = (TrackItem) o;

        return mName != null ? mName.equals(that.mName) : that.mName == null;
    }
}
