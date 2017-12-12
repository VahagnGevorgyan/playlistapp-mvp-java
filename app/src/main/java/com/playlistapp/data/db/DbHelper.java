package com.playlistapp.data.db;

import com.playlistapp.data.db.model.TrackDao;
import com.playlistapp.data.network.data.track.TrackItem;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class DbHelper implements IDbHelper {

    private final TrackDao mTrackDao;

    @Inject
    public DbHelper(TrackDao trackDao) {
        mTrackDao = trackDao;
    }

    @Override
    public Observable<List<TrackItem>> getAllTracks() {
        return Observable.fromCallable(mTrackDao::loadAllTracks);
    }

    @Override
    public Observable<Boolean> saveTrack(final TrackItem track) {
        return Observable.fromCallable(() -> {
            mTrackDao.insertTrack(track);
            return true;
        });
    }

    @Override
    public Observable<Boolean> deleteTrack(final TrackItem track) {
        return Observable.fromCallable(() -> {
            mTrackDao.deleteTrack(track);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveTrackList(final List<TrackItem> trackItems) {
        return Observable.fromCallable(() -> {
            mTrackDao.insertAll(trackItems);
            return true;
        });
    }

}
