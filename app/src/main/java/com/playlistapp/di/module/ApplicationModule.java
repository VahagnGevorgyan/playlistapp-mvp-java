package com.playlistapp.di.module;

import android.app.Application;
import android.content.Context;

import com.playlistapp.Constants;
import com.playlistapp.R;
import com.playlistapp.data.AppDataManager;
import com.playlistapp.data.DataManager;
import com.playlistapp.data.db.AppDbHelper;
import com.playlistapp.data.db.DbHelper;
import com.playlistapp.data.settings.AppSettings;
import com.playlistapp.data.settings.AppSettingsHelper;
import com.playlistapp.di.ApplicationContext;
import com.playlistapp.di.db.DatabaseInfo;
import com.playlistapp.di.PreferenceInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


@Module(includes = {
        com.playlistapp.di.api.ApiModule.class,
        com.playlistapp.di.db.DatabaseModule.class
})
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return Constants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    AppSettingsHelper provideAppSettingsHelper(AppSettings appAppSettingsHelper) {
        return appAppSettingsHelper;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/fonts.source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

}
