package com.playlistapp.data.network.data.track;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "tracks")
public class TrackItem {
    @Expose
    @SerializedName("id")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private Long mId;
    @SerializedName("name")
    @Expose
    @NonNull
    @ColumnInfo(name = "name")
    private String mName;
    @SerializedName("duration")
    @Expose
    @NonNull
    @ColumnInfo(name = "duration")
    private String mDuration;
    @SerializedName("listeners")
    @Expose
    @NonNull
    @ColumnInfo(name = "listeners")
    private String mListeners;
    @SerializedName("mbid")
    @Expose
    @NonNull
    @ColumnInfo(name = "b_id")
    private String mBid;
    @SerializedName("url")
    @Expose
    @NonNull
    @ColumnInfo(name = "url")
    private String mUrl;
    @SerializedName("streamable")
    @Expose
    @Ignore
    private StreamAble mStreamAble;
    @SerializedName("artist")
    @Expose
    @Ignore
    private Artist mArtist;
    @SerializedName("image")
    @Expose
    @Ignore
    private List<Image> mImageList = null;
    @SerializedName("isFavorite")
    @Expose
    @Ignore
    private boolean mIsFavorite;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public String getListeners() {
        return mListeners;
    }

    public void setListeners(String listeners) {
        mListeners = listeners;
    }

    public String getBid() {
        return mBid;
    }

    public void setBid(String bId) {
        mBid = bId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
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
                ", mIsFavorite = " + mIsFavorite +
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
