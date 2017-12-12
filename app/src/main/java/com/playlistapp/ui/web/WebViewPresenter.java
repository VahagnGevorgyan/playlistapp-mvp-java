package com.playlistapp.ui.web;


import com.playlistapp.data.IDataManager;
import com.playlistapp.data.scheduler.ISchedulerProvider;
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
            IDataManager IDataManager,
            ISchedulerProvider ISchedulerProvider,
            CompositeDisposable compositeDisposable
    ) {
        super(IDataManager, ISchedulerProvider, compositeDisposable);
    }

    @Override
    public void onPageFinished() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().hideProgressBar();
    }
}
