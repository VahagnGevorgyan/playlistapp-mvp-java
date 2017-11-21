package com.playlistapp.data.db;


import com.playlistapp.data.db.model.Question;

import java.util.List;

import io.reactivex.Observable;


public interface DbHelper {

    Observable<List<Question>> getAllQuestions();

    Observable<Boolean> saveQuestion(Question question);

    Observable<Boolean> saveQuestionList(List<Question> questionList);

}
