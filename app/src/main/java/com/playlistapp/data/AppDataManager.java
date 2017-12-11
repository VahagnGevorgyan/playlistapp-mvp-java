package com.playlistapp.data;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.playlistapp.data.db.DbHelper;
import com.playlistapp.data.db.model.Question;
import com.playlistapp.data.network.api.ApiHelper;
import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.data.network.data.track.TrackResData;
import com.playlistapp.data.settings.AppSettingsHelper;
import com.playlistapp.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final ApiHelper mApiHelper;
    private final AppSettingsHelper mAppSettingsHelper;
    private final DbHelper mDbHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          ApiHelper apiHelper,
                          AppSettingsHelper appSettingsHelper,
                          DbHelper dbHelper) {
        mContext = context;
        mApiHelper = apiHelper;
        mAppSettingsHelper = appSettingsHelper;
        mDbHelper = dbHelper;
    }


    @Override
    public ApiHelper getApiHelper() {
        return mApiHelper;
    }

    @Override
    public AppSettingsHelper getSettingsHelper() {
        return mAppSettingsHelper;
    }

    @Override
    public DbHelper getDbHelper() {
        return mDbHelper;
    }

    @Override
    public Observable<TrackResData> doTracksApiCall(@NonNull String country,
                                                    @Nullable Integer limit,
                                                    @Nullable Integer page) {
        return mApiHelper.doTrackListCall(country, limit, page);
    }


    @Override
    public Observable<List<TrackItem>> getAllTracks() {
        return mDbHelper.getAllTracks();
    }

    @Override
    public Observable<Boolean> saveTrack(TrackItem trackItem) {
        return mDbHelper.saveTrack(trackItem);
    }

    @Override
    public Observable<Boolean> saveTrackList(List<TrackItem> trackItems) {
        return mDbHelper.saveTrackList(trackItems);
    }

    @Override
    public void setUserAsLoggedOut() {
        setCurrentUserLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        mAppSettingsHelper.profile().clearUserData();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode loggedInMode) {
        mAppSettingsHelper.profile().setCurrentUserLoggedInMode(loggedInMode);
    }

    @Override
    public void setUserId(int userId) {
        mAppSettingsHelper.profile().setUserId(userId);
    }

    @Override
    public void setAuthToken(String authToken) {
        mAppSettingsHelper.profile().setAuthToken(authToken);
    }

    @Override
    public boolean isUserRegistered() {
        return mAppSettingsHelper.profile().isUserRegistered();
    }

    @Override
    public boolean isUserRegisterCompleted() {
        return mAppSettingsHelper.profile().isUserRegisterCompleted();
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mAppSettingsHelper.profile().getCurrentUserLoggedInMode();
    }


    @Override
    public void setRegisterCompleted(boolean isRegisterCompleted) {
        mAppSettingsHelper.profile().setRegisterCompleted(isRegisterCompleted);
    }

    @Override
    public void setRegisterKey(String key) {
        mAppSettingsHelper.profile().setRegKey(key);
    }

}
