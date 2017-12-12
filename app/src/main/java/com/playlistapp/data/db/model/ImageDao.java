package com.playlistapp.data.db.model;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.playlistapp.data.network.data.track.Image;

import java.util.List;

/**
 * SQLite table images DAO interface.
 */
@Dao
public interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    int insertImage(Image image);

    @Query("SELECT * FROM images")
    List<Image> loadAllImages();
}
