package com.playlistapp.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;

import timber.log.Timber;

/**
 * TODO : Test service for periodic requests.
 *
 */
public class TestService extends JobService {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("::: Service created");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("::: Service destroyed");
    }

    /**
     * When the app's MainActivity is created, it starts this service. This is so that the
     * activity and this service can communicate back and forth. See "setUiCallback()"
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timber.d("::: onStart Command ");
        return START_NOT_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Timber.d("::: on Start job");
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Timber.d("::: on Stop job");
        return false;
    }
}
