package com.playlistapp.data.db.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * SQLite table rooms DAO interface.
 */
@Dao
public interface QuestionDao {

    @Query("SELECT * FROM questions ORDER BY text ASC")
    List<Question> loadAllQuestions();

    @Query("SELECT * FROM questions where Id = :questionId")
    Question loadQuestion(int questionId);

    @Insert
    void insertAll(List<Question> questions);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertQuestion(Question question);

    @Delete
    int deleteQuestion(Question question);

    @Query("DELETE FROM questions")
    int deleteAllQuestions();
}
