package com.playlistapp.data;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.playlistapp.data.db.IDbHelper;
import com.playlistapp.data.network.api.ApiHelper;
import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.data.network.data.track.TrackResData;
import com.playlistapp.data.settings.IAppSettingsHelper;
import com.playlistapp.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class DataManager implements IDataManager {

    private final Context mContext;
    private final ApiHelper mApiHelper;
    private final IAppSettingsHelper mIAppSettingsHelper;
    private final IDbHelper mIDbHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       ApiHelper apiHelper,
                       IAppSettingsHelper IAppSettingsHelper,
                       IDbHelper IDbHelper) {
        mContext = context;
        mApiHelper = apiHelper;
        mIAppSettingsHelper = IAppSettingsHelper;
        mIDbHelper = IDbHelper;
    }


    @Override
    public ApiHelper getApiHelper() {
        return mApiHelper;
    }

    @Override
    public IAppSettingsHelper getSettingsHelper() {
        return mIAppSettingsHelper;
    }

    @Override
    public IDbHelper getDbHelper() {
        return mIDbHelper;
    }

    @Override
    public Observable<TrackResData> doTracksApiCall(@NonNull String country,
                                                    @Nullable Integer limit,
                                                    @Nullable Integer page) {
        return mApiHelper.doTrackListCall(country, limit, page);
    }


    @Override
    public Observable<List<TrackItem>> getAllTracks() {
        return mIDbHelper.getAllTracks();
    }

    @Override
    public Observable<Boolean> updateTrack(TrackItem trackItem, boolean remove) {
        if (remove) {
            return mIDbHelper.deleteTrack(trackItem);
        }
        return mIDbHelper.saveTrack(trackItem);
    }

    @Override
    public Observable<Boolean> saveTrackList(List<TrackItem> trackItems) {
        return mIDbHelper.saveTrackList(trackItems);
    }
}
