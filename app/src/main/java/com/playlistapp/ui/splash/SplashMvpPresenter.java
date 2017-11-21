package com.playlistapp.ui.splash;

import com.playlistapp.di.PerActivity;
import com.playlistapp.ui.base.MvpPresenter;

@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
}
