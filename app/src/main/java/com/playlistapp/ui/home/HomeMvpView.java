package com.playlistapp.ui.home;


import com.playlistapp.ui.base.MvpView;

public interface HomeMvpView extends MvpView {

    void showAboutFragment();

    void showSettingsFragment();

    void showTracksFragment();

    void lockDrawer();

    void unlockDrawer();
}
