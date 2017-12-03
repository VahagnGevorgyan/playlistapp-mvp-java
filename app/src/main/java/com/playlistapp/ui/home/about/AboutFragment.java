package com.playlistapp.ui.home.about;


import android.os.Bundle;
import android.view.View;

import com.playlistapp.R;
import com.playlistapp.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * About fragment class.
 */
public class AboutFragment extends BaseFragment implements AboutMvpView {

    public static final String TAG = AboutFragment.class.getSimpleName();

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    AboutMvpPresenter<AboutMvpView> mPresenter;

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initInjector(View view) {
        Timber.d("Injecting \"About\" fragment");
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this, view));
        mPresenter.onAttach(this);
    }

    @Override
    protected void prepareView(View rootView) {
        Timber.d("Preparing fragment elements");
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
