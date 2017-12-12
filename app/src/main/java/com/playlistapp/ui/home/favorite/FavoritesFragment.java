package com.playlistapp.ui.home.favorite;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.playlistapp.R;
import com.playlistapp.data.network.data.track.TrackItem;
import com.playlistapp.eventbus.event.FavoriteClickedEvent;
import com.playlistapp.ui.adapter.TrackListAdapter;
import com.playlistapp.ui.home.HomeBaseFragment;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.playlistapp.Constants.EXTRA_FRAGMENT_POSITION;
import static com.playlistapp.Constants.EXTRA_MENU_ITEM_ID;
import static com.playlistapp.utils.FragmentUtils.FAVORITES_POSITION;


/**
 * Favorites fragment class.
 */
public class FavoritesFragment extends HomeBaseFragment implements FavoritesMvpView {

    public static final String TAG = FavoritesFragment.class.getSimpleName();

    @Inject
    FavoritesMvpPresenter<FavoritesMvpView> mPresenter;

    @Inject
    TrackListAdapter mAdapter;

    @Inject
    LinearLayoutManager mLayoutManager;

    @BindView(R.id.recyclerViewFavorites)
    RecyclerView mRecyclerViewFavorites;
    @BindView(R.id.favoritesPullToRefresh)
    SwipeRefreshLayout mFavoritesPullToRefresh;

    boolean mIsLoading = false;

    public static FavoritesFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_FRAGMENT_POSITION, FAVORITES_POSITION);
        args.putInt(EXTRA_MENU_ITEM_ID, id);
        FavoritesFragment fragment = new FavoritesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_favorite;
    }

    @Override
    protected void initInjector(View view) {
        Timber.d("Injecting \"Favorites\" fragment");
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
    }

    @Override
    protected void prepareView(View rootView) {
        Timber.d("Preparing fragment elements");
        prepareFavoritesAdapter();
        prepareSwipeLayout();
        mPresenter.loadFavoriteItems();
    }

    /**
     * Prepares Pull to refresh listener.
     */
    private void prepareSwipeLayout() {
        Timber.d("Preparing Pull to refresh layout listeners");
        mFavoritesPullToRefresh.setOnRefreshListener(() -> {
            Timber.d("Trying to refresh order items list");
            mPresenter.loadFavoriteItems();
        });
    }

    /**
     * Prepares Favorites list view adapter.
     */
    private void prepareFavoritesAdapter() {
        Timber.d("Preparing \"Favorites\" list view adapter");
        mRecyclerViewFavorites.setLayoutManager(mLayoutManager);
        mRecyclerViewFavorites.setAdapter(mAdapter);
        mRecyclerViewFavorites.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0) {
                    int totalItemCount = mLayoutManager.getItemCount();
                    int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                    if (!mIsLoading && totalItemCount <= (lastVisibleItem + 5)) {
                        mPresenter.nextItems();
                        mIsLoading = true;
                    }
                }
            }
        });
    }

    @Override
    public void updateItems(List<TrackItem> items) {
        Timber.d("Updating favorite list items " + items);
        mFavoritesPullToRefresh.post(
                () -> mFavoritesPullToRefresh.setRefreshing(false));
        mAdapter.updateTrackList(items);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addItems(List<TrackItem> items) {
        Timber.d("Adding new items to favorite list items " + items);
        mAdapter.addTrackList(items);
        mAdapter.notifyDataSetChanged();
        mIsLoading = false;
    }

    @Subscribe
    public void onFavoriteClickedEvent(FavoriteClickedEvent event) {
        Timber.d("Favorite item is clicked " + event.getItem());
        mPresenter.setFavoriteItem(event.getItem());
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
