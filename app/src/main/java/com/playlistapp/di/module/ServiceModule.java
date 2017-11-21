package com.playlistapp.di.module;

import android.app.Service;

import dagger.Module;


@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }
}
