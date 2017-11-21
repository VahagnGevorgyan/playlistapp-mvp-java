package com.playlistapp.ui.home.tracks;


import com.playlistapp.ui.base.MvpPresenter;

public interface TracksMvpPresenter<V extends TracksMvpView> extends MvpPresenter<V> {

    void loadTrackItems();
}
