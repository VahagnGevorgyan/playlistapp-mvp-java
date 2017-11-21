package com.playlistapp.data;


import com.playlistapp.data.db.DbHelper;
import com.playlistapp.data.db.model.Question;
import com.playlistapp.data.network.api.ApiHelper;
import com.playlistapp.data.settings.AppSettingsHelper;

import java.util.List;

import io.reactivex.Observable;

public interface DataManager {

    ApiHelper getApiHelper();

    AppSettingsHelper getSettingsHelper();

    DbHelper getDbHelper();

//    Observable<List<CityItem>> doCitiesApiCall();

//    Observable<LoginData> doLoginApiCall(LoginRequest req);


    Observable<List<Question>> getAllQuestions();

    Observable<Boolean> saveQuestion(Question question);

    Observable<Boolean> saveQuestionList(List<Question> questionList);

    void setUserAsLoggedOut();


    void setCurrentUserLoggedInMode(LoggedInMode loggedInMode);

    void setUserId(int userId);

    void setAuthToken(String authToken);

    boolean isUserRegistered();

    boolean isUserRegisterCompleted();

    void setRegisterCompleted(boolean isRegisterCompleted);

    void setRegisterKey(String key);

    int getCurrentUserLoggedInMode();


    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
