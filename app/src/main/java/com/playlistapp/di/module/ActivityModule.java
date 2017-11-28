package com.playlistapp.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;


import com.playlistapp.data.scheduler.AppSchedulerProvider;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.di.ActivityContext;
import com.playlistapp.di.PerActivity;
import com.playlistapp.ui.adapter.TrackListAdapter;
import com.playlistapp.ui.home.HomeMvpPresenter;
import com.playlistapp.ui.home.HomeMvpView;
import com.playlistapp.ui.home.HomePresenter;
import com.playlistapp.ui.home.settings.SettingsMvpPresenter;
import com.playlistapp.ui.home.settings.SettingsMvpView;
import com.playlistapp.ui.home.settings.SettingsPresenter;
import com.playlistapp.ui.home.tracks.TracksMvpPresenter;
import com.playlistapp.ui.home.tracks.TracksMvpView;
import com.playlistapp.ui.home.tracks.TracksPresenter;
import com.playlistapp.ui.splash.SplashMvpPresenter;
import com.playlistapp.ui.splash.SplashMvpView;
import com.playlistapp.ui.splash.SplashPresenter;
import com.playlistapp.ui.web.WebViewMvpPresenter;
import com.playlistapp.ui.web.WebViewMvpView;
import com.playlistapp.ui.web.WebViewPresenter;
import com.playlistapp.utils.network.NetworkStateHelper;
import com.playlistapp.utils.network.NetworkStateManager;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(
            SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    HomeMvpPresenter<HomeMvpView> provideHomePresenter(
            HomePresenter<HomeMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    TracksMvpPresenter<TracksMvpView> provideTracksPresenter(
            TracksPresenter<TracksMvpView> presenter) {
        return presenter;
    }

    @Provides
    TrackListAdapter provideTrackListAdapter(AppCompatActivity activity) {
        return new TrackListAdapter(activity);
    }

    @Provides
    @PerActivity
    SettingsMvpPresenter<SettingsMvpView> provideSettingsPresenter(
            SettingsPresenter<SettingsMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    WebViewMvpPresenter<WebViewMvpView> provideWebViewPresenter(
            WebViewPresenter<WebViewMvpView> presenter) {
        return presenter;
    }


//    @Provides
//    @PerActivity
//    PersonRegMvpPresenter<PersonRegMvpView> providePersonRegPresenter(
//            PersonRegPresenter<PersonRegMvpView> presenter) {
//        return presenter;
//    }

    @Provides
    @PerActivity
    NetworkStateHelper provideNetworkHelper(
            NetworkStateManager networkStateManager) {
        return networkStateManager;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

//    @Provides
//    NotificationReceiver provideNotificationReceiver() {
//        return new NotificationReceiver();
//    }
}
