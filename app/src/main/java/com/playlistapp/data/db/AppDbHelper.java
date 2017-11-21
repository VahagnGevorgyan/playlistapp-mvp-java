package com.playlistapp.data.db;

import com.playlistapp.data.db.model.Question;
import com.playlistapp.data.db.model.QuestionDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class AppDbHelper implements DbHelper {

    private final QuestionDao mQuestionDao;

    @Inject
    public AppDbHelper(QuestionDao questionDao) {
        mQuestionDao = questionDao;
    }

    @Override
    public Observable<List<Question>> getAllQuestions() {
        return Observable.fromCallable(mQuestionDao::loadAllQuestions);
    }

    @Override
    public Observable<Boolean> saveQuestion(final Question question) {
        return Observable.fromCallable(() -> {
            mQuestionDao.insertQuestion(question);
            return true;
        });
    }

    @Override
    public Observable<Boolean> saveQuestionList(final List<Question> questionList) {
        return Observable.fromCallable(() -> {
            mQuestionDao.insertAll(questionList);
            return true;
        });
    }

}
