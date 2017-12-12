package com.playlistapp.ui.home.tracks;


import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.ui.base.MvpView;

import java.util.List;

public interface TracksMvpView extends MvpView {

    void updateTracks(List<TrackItem> trackItems);

    void addTracks(List<TrackItem> trackItems);

    void trackItemUpdated(TrackItem item, int position);
}
