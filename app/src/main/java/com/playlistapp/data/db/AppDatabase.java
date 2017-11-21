package com.playlistapp.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.playlistapp.data.db.model.Question;
import com.playlistapp.data.db.model.QuestionDao;

import static com.playlistapp.Constants.DATABASE_VERSION;

/**
 * Application database.
 */
@Database(entities = {Question.class}, version = DATABASE_VERSION, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {

    public abstract QuestionDao questionDao();
}
