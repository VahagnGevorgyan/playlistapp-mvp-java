package com.playlistapp.data.db.model;


import android.arch.persistence.room.Query;

import java.util.List;

public interface TrackDao {

    @Query("SELECT * FROM tracks ORDER BY id ASC")
    List<Question> loadAllTracks();
}
