package com.playlistapp.di.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.playlistapp.Constants;
import com.playlistapp.data.db.AppDatabase;
import com.playlistapp.data.db.AppDbHelper;
import com.playlistapp.data.db.DbHelper;
import com.playlistapp.data.db.model.TrackDao;
import com.playlistapp.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module which provides SQLite database.
 */
@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase providesDataBase(@ApplicationContext Context context, @DatabaseInfo String dbName) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return Constants.DATABASE_FILE_NAME;
    }

    @Provides
    @Singleton
    TrackDao provideTrackDao(AppDatabase database) {
        return database.trackDao();
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(TrackDao trackDao) {
        return new AppDbHelper(trackDao);
    }
}
