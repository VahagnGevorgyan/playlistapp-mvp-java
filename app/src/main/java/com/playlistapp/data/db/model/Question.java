package com.playlistapp.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;


@Entity(tableName = "questions")
public class Question {

    @Expose
    @SerializedName("id")
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "Id")
    private Long id;

    @Expose
    @SerializedName("question_text")
    @NonNull
    @ColumnInfo(name = "Text")
    private String questionText;

    @Expose
    @SerializedName("question_img_url")
    @NonNull
    @Ignore
    private String imgUrl;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return this.questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}