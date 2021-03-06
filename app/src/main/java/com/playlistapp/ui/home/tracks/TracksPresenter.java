package com.playlistapp.ui.home.tracks;

import com.playlistapp.data.IDataManager;
import com.playlistapp.data.network.data.error.ApiError;
import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.data.network.data.track.TrackResData;
import com.playlistapp.data.scheduler.ISchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

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
            IDataManager dataManager,
            ISchedulerProvider schedulerProvider,
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

    @Override
    public void setFavoriteItem(TrackItem item, int position) {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showProgressBar();

        getCompositeDisposable().add(getDataManager()
                .updateTrack(item, item.isFavorite())
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(aBoolean -> {
                    Timber.d("Updating track item was successful " + aBoolean);

                    getMvpView().hideProgressBar();
                    getMvpView().trackItemUpdated(item, position);
                }, throwable -> {
                    Timber.e("Updating track item was failed " + throwable.getMessage());

                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideProgressBar();
                    getMvpView().showMessage(throwable.getMessage());
                }));
    }

    /**
     * Requesting to Tracks Api web service.
     */
    private void callTracksApi(int pageId, Consumer<TrackResData> listener) {
        Timber.d("Calling Tracks web service");

        Timber.d(":: page " + pageId);
        Timber.d(":: country " + getDataManager().getSettingsHelper().search().getCountry());
        Timber.d(":: limit " + getDataManager().getSettingsHelper().search().getLimitCount());

        getCompositeDisposable().add(getDataManager()
                .doTracksApiCall(
                        getDataManager().getSettingsHelper().search().getCountry(),
                        getDataManager().getSettingsHelper().search().getLimitCount(),
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
