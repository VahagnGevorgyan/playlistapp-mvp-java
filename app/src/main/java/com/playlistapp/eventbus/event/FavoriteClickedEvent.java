package com.playlistapp.eventbus.event;


import com.playlistapp.data.network.data.track.TrackItem;

public class FavoriteClickedEvent {

    private TrackItem mItem;
    private int mPosition;

    public FavoriteClickedEvent(TrackItem item, int position) {
        mItem = item;
        mPosition = position;
    }

    public TrackItem getItem() {
        return mItem;
    }

    public int getPosition() {
        return mPosition;
    }
}
