package com.playlistapp.di.component;

import android.app.Application;
import android.content.Context;

import com.playlistapp.App;
import com.playlistapp.data.IDataManager;
import com.playlistapp.di.ApplicationContext;
import com.playlistapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

//    void inject(GcmIntentService service);

    @ApplicationContext
    Context context();

    Application application();

    IDataManager getDataManager();
}