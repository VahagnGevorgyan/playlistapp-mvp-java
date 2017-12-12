package com.playlistapp.di.module;

import android.app.Application;
import android.content.Context;

import com.playlistapp.Constants;
import com.playlistapp.R;
import com.playlistapp.data.DataManager;
import com.playlistapp.data.IDataManager;
import com.playlistapp.data.settings.AppSettingsHelper;
import com.playlistapp.data.settings.IAppSettingsHelper;
import com.playlistapp.di.ApplicationContext;
import com.playlistapp.di.PreferenceInfo;
import com.playlistapp.di.api.ApiModule;
import com.playlistapp.di.db.DatabaseModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


@Module(includes = {
        ApiModule.class,
        DatabaseModule.class
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
    IDataManager provideDataManager(DataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    IAppSettingsHelper provideAppSettingsHelper(AppSettingsHelper appAppSettingsHelper) {
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
