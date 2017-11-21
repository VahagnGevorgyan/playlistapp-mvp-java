package com.playlistapp.ui.splash;

import com.playlistapp.data.DataManager;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Presenter class for Splash activity.
 * @param <V>
 */
public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    /**
     * Timeout parameter for delaying before starting next activity.
     */
    private static final int TIMEOUT = 2000;

    @Inject
    public SplashPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable
    ) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        getMvpView().onSplashAttached(TIMEOUT);
    }
}
