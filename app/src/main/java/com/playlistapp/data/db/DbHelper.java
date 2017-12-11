package com.playlistapp.data.db;


import com.playlistapp.data.network.data.track.TrackItem;

import java.util.List;

import io.reactivex.Observable;


public interface DbHelper {

    Observable<List<TrackItem>> getAllTracks();

    Observable<Boolean> saveTrack(TrackItem trackItem);

    Observable<Boolean> saveTrackList(List<TrackItem> trackItems);

}
