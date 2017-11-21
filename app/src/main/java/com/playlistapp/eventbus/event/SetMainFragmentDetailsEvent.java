package com.playlistapp.eventbus.event;


public class SetMainFragmentDetailsEvent {

    private int mFragmentPosition;
    private int mMenuItem;

    public SetMainFragmentDetailsEvent(int pos, int mMenuItem) {
        this.mFragmentPosition = pos;
        this.mMenuItem = mMenuItem;
    }

    public int getFragmentPosition() {
        return this.mFragmentPosition;
    }

    public int getMenuItem() {
        return this.mMenuItem;
    }
}
