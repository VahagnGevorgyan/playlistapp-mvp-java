package com.playlistapp;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.playlistapp.di.api.ApiModule;
import com.playlistapp.di.component.ApplicationComponent;
import com.playlistapp.di.component.DaggerApplicationComponent;
import com.playlistapp.di.module.ApplicationModule;
import com.playlistapp.eventbus.SingletonBus;
import com.playlistapp.logging.DevelopmentTree;
import com.playlistapp.logging.ProductionTree;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Application file
 */
public class App extends Application {

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    public static App mContext;
    protected ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.d("Creating Application");

        mContext = this;

        initializeEventBus();
        initializeFabric();
        initializeInjector();
        initializeCalligraphy();
    }

    /**
     * Initializing {@link com.squareup.otto.Bus}
     */
    private void initializeEventBus() {
        Timber.d("Initializing EventBus");

        SingletonBus.initialize();
    }

    /**
     * Initializing Injector.
     */
    private void initializeInjector() {
        Timber.d("Initializing ApplicationComponent");
        mAppComponent = buildComponent();
        mAppComponent.inject(this);
    }

    protected ApplicationComponent buildComponent() {
        Timber.d("Build App Component");
        return mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule(this))
                .build();
    }

    /**
     *  Initializing {@link Fabric} + {@link Crashlytics} + {@link Timber}
     */
    private void initializeFabric() {
        Timber.d("Initializing Fabric + Crashlytics + Timber");

        Fabric.with(this, new Crashlytics());
        Fabric.with(this, new Answers(), new Crashlytics());
        if(BuildConfig.DEBUG) {
            Timber.plant(new DevelopmentTree());
        } else {
            Timber.plant(new ProductionTree());
        }
    }

    /**
     * Initializing {@link uk.co.chrisjenx.calligraphy.CalligraphyConfig}.
     */
    private void initializeCalligraphy() {
        Timber.d("Initializing CalligraphyConfig");
        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }


    /**
     *  Returns application context.
     */
    public static App getContext() {
        return mContext;
    }

    /**
     * Returns Dagger App Component instance.
     *
     * @return
     */
    public static ApplicationComponent getAppComponent() {
        return getContext().mAppComponent;
    }


    /**
     * Needed to replace the component with a test specific one.
     *
     * @param applicationComponent
     */
    //
    public void setComponent(ApplicationComponent applicationComponent) {
        mAppComponent = applicationComponent;
    }
}
