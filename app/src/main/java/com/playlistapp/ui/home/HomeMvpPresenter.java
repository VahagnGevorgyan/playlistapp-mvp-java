package com.playlistapp.ui.home;


import com.playlistapp.di.PerActivity;
import com.playlistapp.ui.base.MvpPresenter;

@PerActivity
public interface HomeMvpPresenter<V extends HomeMvpView> extends MvpPresenter<V> {

    void onNavMenuCreated();

    void testInterval();

    void finishInterval();
}
