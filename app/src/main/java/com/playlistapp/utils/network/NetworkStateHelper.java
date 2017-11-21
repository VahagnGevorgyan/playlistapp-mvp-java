package com.playlistapp.utils.network;


import com.playlistapp.di.PerActivity;

@PerActivity
public interface NetworkStateHelper {

    void initializeNetworkStatusListener();

    void recheckNetwork();
}
