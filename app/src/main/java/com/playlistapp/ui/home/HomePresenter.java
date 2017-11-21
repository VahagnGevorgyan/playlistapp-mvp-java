package com.playlistapp.ui.home;

import com.playlistapp.data.DataManager;
import com.playlistapp.data.db.model.Question;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;
import com.playlistapp.utils.CollectionUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Presenter class for Home activity.
 * @param <V>
 */
public class HomePresenter<V extends HomeMvpView> extends BasePresenter<V>
        implements HomeMvpPresenter<V> {

    @Inject
    public HomePresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable
    ) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onNavMenuCreated() {
        if (!isViewAttached()) {
            return;
        }

//        Question question = new Question();
//        question.setId(1L);
//        question.setQuestionText("Question 1");
//        getCompositeDisposable().add(getDataManager()
//                .saveQuestion(question)
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(inserted -> {
//                    if (!isViewAttached()) {
//                        return;
//                    }
//                    Timber.d(":: inserted " + inserted);
//                }));


//        // TODO: TEST DB helper class
//        getCompositeDisposable().add(getDataManager()
//                .getAllQuestions()
//                .subscribeOn(getSchedulerProvider().io())
//                .observeOn(getSchedulerProvider().ui())
//                .subscribe(questionList -> {
//                    if (!isViewAttached()) {
//                        return;
//                    }
//
//                    Timber.d(":: questionList : " + questionList);
//                    if (!CollectionUtils.isEmpty(questionList)) {
//                        Timber.d(":: text " + questionList.get(0).getQuestionText());
//                    }
////                        if (questionList != null) {
////                            getMvpView().reloadQuestionnaire(questionList);
////                        }
//                }));
//        getDataManager().getDbHelper().getAllQuestions();

        // TODO: open after testing DB
        getMvpView().showTracksFragment();
    }
}
