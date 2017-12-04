package com.playlistapp.ui.home.tracks;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.playlistapp.R;
import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.eventbus.event.RefreshTracksEvent;
import com.playlistapp.ui.adapter.TrackListAdapter;
import com.playlistapp.ui.base.MainBaseFragment;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.playlistapp.Constants.EXTRA_FRAGMENT_POSITION;
import static com.playlistapp.Constants.EXTRA_MENU_ITEM_ID;
import static com.playlistapp.utils.FragmentUtils.DEFAULT_POSITION;
import static com.playlistapp.utils.FragmentUtils.TRACKS_POSITION;

/**
 * Tracks fragment class.
 */
public class TracksFragment extends MainBaseFragment implements TracksMvpView {

    public static final String TAG = TracksFragment.class.getSimpleName();

    @Inject
    TracksMvpPresenter<TracksMvpView> mPresenter;

    @Inject
    TrackListAdapter mAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recyclerViewTracks)
    RecyclerView mRecyclerViewTracks;
    @BindView(R.id.tracksPullToRefresh)
    SwipeRefreshLayout mTracksPullToRefresh;

    boolean mIsLoading = false;

    public static TracksFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_FRAGMENT_POSITION, TRACKS_POSITION);
        args.putInt(EXTRA_MENU_ITEM_ID, id);
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
        mRecyclerViewTracks.setAdapter(mAdapter);
        mRecyclerViewTracks.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) {
                    int totalItemCount = mLayoutManager.getItemCount();
                    int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                    if (!mIsLoading && totalItemCount <= (lastVisibleItem + 5)) {
                        mPresenter.nextTrackItems();
                        mIsLoading = true;
                    }
                }
            }
        });
    }

    @Override
    public void updateTracks(List<TrackItem> trackItems) {
        Timber.d("Updating track list items " + trackItems);
        mTracksPullToRefresh.post(
                () -> mTracksPullToRefresh.setRefreshing(false));
        mAdapter.updateTrackList(trackItems);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addTracks(List<TrackItem> trackItems) {
        Timber.d("Adding new items to track list items " + trackItems);
        mAdapter.addTrackList(trackItems);
        mAdapter.notifyDataSetChanged();
        mIsLoading = false;
    }

    @Subscribe
    public void onRefreshTracksEvent(RefreshTracksEvent event) {
        Timber.d("Trying to refresh track list items");
        mPresenter.loadTrackItems();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
