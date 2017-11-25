package com.playlistapp.ui.home.tracks;

import com.playlistapp.data.DataManager;
import com.playlistapp.data.network.data.error.ApiError;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Presenter class for Tracks fragment.
 * @param <V>
 */
public class TracksPresenter<V extends TracksMvpView> extends BasePresenter<V>
        implements TracksMvpPresenter<V> {

    @Inject
    public TracksPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable
    ) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadTrackItems() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showProgressBar();

        getCompositeDisposable().add(getDataManager()
                .doTracksApiCall("spain", getDataManager().getSettingsHelper().general().getTrackLimitCount())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(trackResData -> {
                    Timber.d("Api request \"doTracksApiCall\" was successful " + trackResData);

                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideProgressBar();

//                    Timber.d(":: list : " + trackFeed.getTrackItems());
//                    Timber.d(":: id : " + trackFeed.getTrackItems().get(0).getId());
//                    Timber.d(":: name : " + trackFeed.getTrackItems().get(0).getName());
//                    Timber.d(":: title : " + trackFeed.getTrackItems().get(0).getTitle());
//                    Timber.d(":: price : " + trackFeed.getTrackItems().get(0).getPrice());
//                    Timber.d(":: amount : " + trackFeed.getTrackItems().get(0).getAmount());
//                    Timber.d(":: rights : " + trackFeed.getTrackItems().get(0).getRights());

                    //                    getMvpView().updateOrderDetailsList(orderDetails);

                }, throwable -> {
                    Timber.e("Api request \"doTracksApiCall\" was failed " + throwable.getMessage());

                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideProgressBar();
                    if (throwable instanceof ApiError) {
                        handleApiError((ApiError) throwable);
                    }
                }));
    }
}
