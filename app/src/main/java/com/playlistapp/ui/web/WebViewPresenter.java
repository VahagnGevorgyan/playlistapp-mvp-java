package com.playlistapp.ui.web;


import com.playlistapp.data.DataManager;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Presenter class for WebView activity.
 * @param <V>
 */
public class WebViewPresenter <V extends WebViewMvpView> extends BasePresenter<V>
        implements WebViewMvpPresenter<V> {

    @Inject
    public WebViewPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable
    ) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onPageFinished() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().hideProgressBar();
    }
}
