package com.playlistapp.ui.web;


import com.playlistapp.di.PerActivity;
import com.playlistapp.ui.base.MvpPresenter;

@PerActivity
public interface WebViewMvpPresenter<V extends WebViewMvpView> extends MvpPresenter<V> {

    void onPageFinished();
}
