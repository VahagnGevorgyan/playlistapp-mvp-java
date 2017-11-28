package com.playlistapp.ui.home.settings;


import android.os.Bundle;
import android.view.View;

import com.playlistapp.R;
import com.playlistapp.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Settings fragment class.
 */
public class SettingsFragment extends BaseFragment implements SettingsMvpView {

    public static final String TAG = SettingsFragment.class.getSimpleName();

    @Inject
    SettingsMvpPresenter<SettingsMvpView> mPresenter;

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_settings;
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
    }

    @OnClick(R.id.nav_back_btn)
    void onNavBackClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @OnClick(R.id.check_back_btn)
    void onCheckClick() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
