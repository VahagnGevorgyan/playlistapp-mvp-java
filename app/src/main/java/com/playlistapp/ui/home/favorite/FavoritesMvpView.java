package com.playlistapp.ui.home.favorite;


import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.ui.base.MvpView;

import java.util.List;

public interface FavoritesMvpView extends MvpView {

    void updateItems(List<TrackItem> items);

    void addItems(List<TrackItem> items);
}
