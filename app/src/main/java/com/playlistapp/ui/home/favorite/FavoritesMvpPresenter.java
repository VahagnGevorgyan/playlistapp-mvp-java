package com.playlistapp.ui.home.favorite;


import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.ui.base.MvpPresenter;

public interface FavoritesMvpPresenter<V extends FavoritesMvpView> extends MvpPresenter<V> {

    void loadFavoriteItems();

    void nextItems();

    void setFavoriteItem(TrackItem item, int position);
}
