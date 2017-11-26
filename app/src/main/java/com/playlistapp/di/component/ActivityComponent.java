package com.playlistapp.di.component;

import com.playlistapp.di.PerActivity;
import com.playlistapp.di.module.ActivityModule;
import com.playlistapp.ui.home.HomeActivity;
import com.playlistapp.ui.home.tracks.TracksFragment;
import com.playlistapp.ui.splash.SplashActivity;
import com.playlistapp.ui.web.WebViewActivity;
import com.playlistapp.utils.network.NetworkStateManager;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(HomeActivity activity);

    void inject(TracksFragment fragment);

    void inject(WebViewActivity activity);

//    void inject(PersonRegFragment fragment);
//
//    void inject(OfflineDialog offlineDialog);

    void inject(NetworkStateManager networkStateManager);

}
