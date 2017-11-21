package com.playlistapp.data.network.data.notification;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.playlistapp.data.network.data.ApiResponse;

public class NotificationResponse extends ApiResponse {

    @SerializedName("data")
    @Expose
    private NotificationData data;

    public NotificationData getData() {
        return data;
    }

    public void setData(NotificationData data) {
        this.data = data;
    }
}
