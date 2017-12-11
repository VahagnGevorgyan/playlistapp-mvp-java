package com.playlistapp.data.db.model;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.playlistapp.data.network.data.track.TrackItem;

import java.util.List;

/**
 * SQLite table tracks DAO interface.
 */
@Dao
public interface TrackDao {

    @Query("SELECT * FROM tracks ORDER BY id ASC")
    List<TrackItem> loadAllTracks();

    @Query("SELECT * FROM tracks where Id = :trackId")
    TrackItem loadTrack(int trackId);

    @Insert
    void insertAll(List<TrackItem> trackItems);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertTrack(TrackItem trackItem);

    @Delete
    int deleteTrack(TrackItem trackItem);

    @Query("DELETE FROM tracks")
    int deleteAllTracks();
}
