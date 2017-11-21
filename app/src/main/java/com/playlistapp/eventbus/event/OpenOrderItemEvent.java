package com.playlistapp.eventbus.event;


public class OpenOrderItemEvent {

    private String mOrderId;

    public OpenOrderItemEvent(String orderId) {
        this.mOrderId = orderId;
    }

    public String getOrderId() {
        return this.mOrderId;
    }
}
