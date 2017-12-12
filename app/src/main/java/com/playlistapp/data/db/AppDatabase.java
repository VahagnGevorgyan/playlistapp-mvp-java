package com.playlistapp.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.playlistapp.data.db.model.TrackDao;
import com.playlistapp.data.network.data.track.TrackItem;

import static com.playlistapp.Constants.DATABASE_VERSION;

/**
 * Application database.
 */
@Database(entities = {TrackItem.class}, version = DATABASE_VERSION, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TrackDao trackDao();
}
