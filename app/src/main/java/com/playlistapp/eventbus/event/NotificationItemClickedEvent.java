package com.playlistapp.eventbus.event;


import com.playlistapp.data.network.data.notification.Notification;

public class NotificationItemClickedEvent {

    private Notification mNotification;

    public NotificationItemClickedEvent(Notification notification) {
        this.mNotification = notification;
    }

    public Notification getNotification() {
        return this.mNotification;
    }
}
