package com.playlistapp.eventbus.event;


public class OpenPriceDeclarationEvent {

    private String mId;

    public OpenPriceDeclarationEvent(String id) {
        this.mId = id;
    }

    public String getId() {
        return this.mId;
    }
}
