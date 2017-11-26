package com.playlistapp.ui.home.tracks;

import com.playlistapp.data.DataManager;
import com.playlistapp.data.network.data.error.ApiError;
import com.playlistapp.data.network.data.track.TrackResData;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
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

    private int mPageId;

    @Override
    public void loadTrackItems() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showProgressBar();

        mPageId = 1;
        callTracksApi(mPageId, trackResData -> {
            Timber.d("Api request \"doTracksApiCall\" was successful " + trackResData.getTrackItems());

            if (!isViewAttached()) {
                return;
            }
            getMvpView().hideProgressBar();
            getMvpView().updateTracks(trackResData.getTrackItems());
        });
    }

    @Override
    public void nextTrackItems() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showProgressBar();

        mPageId++;
        callTracksApi(mPageId, trackResData -> {
            Timber.d("Api request \"doTracksApiCall\" was successful " + trackResData);

            if (!isViewAttached()) {
                return;
            }
            getMvpView().hideProgressBar();
            getMvpView().addTracks(trackResData.getTrackItems());
        });
    }

    /**
     * Requesting to Tracks Api web service.
     */
    private void callTracksApi(int pageId, Consumer<TrackResData> listener) {
        Timber.d("Calling Tracks web service");
        getCompositeDisposable().add(getDataManager()
                .doTracksApiCall(
                        "spain",
                        getDataManager().getSettingsHelper().general().getTrackLimitCount(),
                        pageId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(
                        listener,
                        throwable -> {
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
