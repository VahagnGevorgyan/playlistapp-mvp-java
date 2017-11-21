package com.playlistapp.ui.home.tracks;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.playlistapp.R;
import com.playlistapp.ui.adapter.TrackListAdapter;
import com.playlistapp.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Tracks fragment class.
 */
public class TracksFragment extends BaseFragment implements TracksMvpView {

    public static final String TAG = TracksFragment.class.getSimpleName();

    @Inject
    TracksMvpPresenter<TracksMvpView> mPresenter;

//    @Inject
//    TrackListAdapter mAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recyclerViewTracks)
    RecyclerView mRecyclerViewTracks;
    @BindView(R.id.tracksPullToRefresh)
    SwipeRefreshLayout mTracksPullToRefresh;

    public static TracksFragment newInstance() {
        Bundle args = new Bundle();
        TracksFragment fragment = new TracksFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_tracks;
    }

    @Override
    protected void initInjector(View view) {
        Timber.d("Injecting \"Tracks\" fragment");
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
    }

    @Override
    protected void prepareView(View rootView) {
        Timber.d("Preparing fragment elements");
        prepareTracksAdapter();
        prepareSwipeLayout();
        mPresenter.loadTrackItems();
    }

    /**
     * Prepares Pull to refresh listener.
     */
    private void prepareSwipeLayout() {
        Timber.d("Preparing Pull to refresh layout listeners");
        mTracksPullToRefresh.setOnRefreshListener(() -> {
            Timber.d("Trying to refresh order items list");
            mPresenter.loadTrackItems();
        });
    }

    /**
     * Prepares Tracks list view adapter.
     */
    private void prepareTracksAdapter() {
        Timber.d("Preparing \"Tracks\" list view adapter");
        mRecyclerViewTracks.setLayoutManager(mLayoutManager);
//        mRecyclerViewTracks.setAdapter(mAdapter);
    }
}
