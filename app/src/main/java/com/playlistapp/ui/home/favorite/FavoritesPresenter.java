package com.playlistapp.ui.home.favorite;

import com.playlistapp.data.DataManager;
import com.playlistapp.data.scheduler.SchedulerProvider;
import com.playlistapp.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Presenter class for Favorites fragment.
 * @param <V>
 */
public class FavoritesPresenter<V extends FavoritesMvpView> extends BasePresenter<V>
        implements FavoritesMvpPresenter<V> {

    @Inject
    public FavoritesPresenter(
            DataManager dataManager,
            SchedulerProvider schedulerProvider,
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

        // TODO : Get Data from local SQLite database
        // TODO : Fetching data ???

    }

    @Override
    public void nextItems() {

    }
}
