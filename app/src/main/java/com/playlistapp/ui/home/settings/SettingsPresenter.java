package com.playlistapp.ui.home.settings;

import com.playlistapp.data.DataManager;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Presenter class for Tracks fragment.
 * @param <V>
 */
public class SettingsPresenter<V extends SettingsMvpView> extends BasePresenter<V>
        implements SettingsMvpPresenter<V> {

    @Inject
    public SettingsPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable
    ) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

}
