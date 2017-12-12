package com.playlistapp.data.network.data.track;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.NotNull;

@Entity(tableName = "images",
        foreignKeys = @ForeignKey(entity = TrackItem.class, parentColumns = "id", childColumns = "track_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE))
public class Image {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @Expose
    private int mId = 0;
    @SerializedName("trackId")
    @ColumnInfo(name = "track_id")
    @Expose
    @NotNull
    private int mTrackId = 0;
    @SerializedName("#text")
    @ColumnInfo(name = "text")
    @Expose
    private String mText;
    @SerializedName("size")
    @ColumnInfo(name = "size")
    @Expose
    private String mSize;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getTrackId() {
        return mTrackId;
    }

    public void setTrackId(int trackId) {
        mTrackId = trackId;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getSize() {
        return mSize;
    }

    public void setSize(String size) {
        mSize = size;
    }
}
