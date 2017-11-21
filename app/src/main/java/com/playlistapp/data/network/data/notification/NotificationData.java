package com.playlistapp.data.network.data.notification;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationData {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("notifications")
    @Expose
    private List<Notification> notifications = null;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}
