package com.playlistapp.ui.home.favorite;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.playlistapp.R;
import com.playlistapp.ui.home.HomeBaseFragment;

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

    @BindView(R.id.recyclerViewFavorites)
    RecyclerView mRecyclerViewFavorites;
    @BindView(R.id.favoritesPullToRefresh)
    SwipeRefreshLayout mFavoritesPullToRefresh;

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
//        prepareFavoritesAdapter();
//        prepareSwipeLayout();
        mPresenter.loadFavoriteItems();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
