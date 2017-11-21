package com.playlistapp.ui.home.tracks;

import com.playlistapp.data.DataManager;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Presenter class for Tracks fragment.
 * @param <V>
 */
public class TracksPresenter<V extends TracksMvpView> extends BasePresenter<V>
        implements TracksMvpPresenter<V> {

    @Inject
    public TracksPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable
    ) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadTrackItems() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showProgressBar();


    }
}
