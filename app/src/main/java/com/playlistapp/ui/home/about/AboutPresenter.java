package com.playlistapp.ui.home.about;

import com.playlistapp.data.IDataManager;
import com.playlistapp.data.scheduler.ISchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Presenter class for About fragment.
 * @param <V>
 */
public class AboutPresenter<V extends AboutMvpView> extends BasePresenter<V>
        implements AboutMvpPresenter<V> {

    @Inject
    public AboutPresenter(
            IDataManager IDataManager,
            ISchedulerProvider ISchedulerProvider,
            CompositeDisposable compositeDisposable
    ) {
        super(IDataManager, ISchedulerProvider, compositeDisposable);
    }

}
