package com.playlistapp.ui.home.favorite;

import com.playlistapp.data.IDataManager;
import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.data.scheduler.ISchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Presenter class for Favorites fragment.
 * @param <V>
 */
public class FavoritesPresenter<V extends FavoritesMvpView> extends BasePresenter<V>
        implements FavoritesMvpPresenter<V> {

    @Inject
    public FavoritesPresenter(
            IDataManager dataManager,
            ISchedulerProvider schedulerProvider,
            CompositeDisposable compositeDisposable
    ) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadFavoriteItems() {
        if (!isViewAttached()) {
            return;
        }
        getMvpView().showProgressBar();

        getCompositeDisposable().add(getDataManager()
                .getAllTracks()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(trackItems -> {
                    Timber.d("Request all \"Tracks\" from local database was successful " + trackItems);

                    getMvpView().hideProgressBar();
                    getMvpView().updateItems(trackItems);

                }, throwable -> {
                    Timber.e("Request all \"Tracks\" from local database was failed " + throwable.getMessage());
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideProgressBar();
                    getMvpView().showMessage(throwable.getMessage());
                }));

    }

    @Override
    public void nextItems() {

    }

    @Override
    public void setFavoriteItem(TrackItem item, int position) {

    }
}
