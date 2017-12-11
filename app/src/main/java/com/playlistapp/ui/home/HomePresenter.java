package com.playlistapp.ui.home;

import com.playlistapp.data.DataManager;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;
import com.playlistapp.utils.CollectionUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Presenter class for Home activity.
 * @param <V>
 */
public class HomePresenter<V extends HomeMvpView> extends BasePresenter<V>
        implements HomeMvpPresenter<V> {

    private Disposable mSubscription;

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

        getMvpView().showTracksFragment();
    }

    @Override
    public void testInterval() {
        Timber.d("Testing RxJava Observable Interval ");


//        Disposable subscription = Observable.interval(5, TimeUnit.SECONDS, Schedulers.io())
//                .map(tick -> lastTick.getAndIncrement())
//                .subscribe(tick -> System.out.println("tick = " + tick));

        mSubscription = Observable
                .interval(15, TimeUnit.SECONDS)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aLong -> {
                    Timber.d(":: time : " + aLong);

                    getCompositeDisposable().add(getDataManager()
                            .getAllTracks()
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(trackItems -> {
                                if (!isViewAttached()) {
                                    return;
                                }

                                Timber.d(":: trackItems : " + trackItems);
                                if (!CollectionUtils.isEmpty(trackItems)) {
                                    Timber.d(":: name : " + trackItems.get(0).getName());
                                }
                            }));
                });
        getCompositeDisposable().add(mSubscription);

    }

    @Override
    public void finishInterval() {
        Timber.d(":: finish interval");
        if (mSubscription != null && !mSubscription.isDisposed()) {
            mSubscription.dispose();
        }
    }
}
