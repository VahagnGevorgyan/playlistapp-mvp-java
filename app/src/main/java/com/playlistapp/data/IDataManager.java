package com.playlistapp.data;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.playlistapp.data.db.IDbHelper;
import com.playlistapp.data.network.api.ApiHelper;
import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.data.network.data.track.TrackResData;
import com.playlistapp.data.settings.IAppSettingsHelper;

import java.util.List;

import io.reactivex.Observable;

public interface IDataManager {

    ApiHelper getApiHelper();

    IAppSettingsHelper getSettingsHelper();

    IDbHelper getDbHelper();

    Observable<TrackResData> doTracksApiCall(@NonNull String country,
                                             @Nullable Integer limit,
                                             @Nullable Integer page);

    Observable<List<TrackItem>> getAllTracks();

    Observable<Boolean> updateTrack(TrackItem trackItem, boolean remove);

    Observable<Boolean> saveTrackList(List<TrackItem> trackItems);
}
