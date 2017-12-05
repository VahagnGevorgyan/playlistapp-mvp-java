package com.playlistapp.ui.home.about;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.playlistapp.BuildConfig;
import com.playlistapp.R;
import com.playlistapp.ui.base.MainBaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

import static com.playlistapp.Constants.EXTRA_FRAGMENT_POSITION;
import static com.playlistapp.Constants.EXTRA_MENU_ITEM_ID;
import static com.playlistapp.utils.FragmentUtils.ABOUT_POSITION;

/**
 * About fragment class.
 */
public class AboutFragment extends MainBaseFragment implements AboutMvpView {

    public static final String TAG = AboutFragment.class.getSimpleName();

    @BindView(R.id.version_number)
    TextView mVersionNumberView;

    public static AboutFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_FRAGMENT_POSITION, ABOUT_POSITION);
        args.putInt(EXTRA_MENU_ITEM_ID, id);
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
        mVersionNumberView.setText(getString(R.string.about_version_number, BuildConfig.VERSION_NAME));
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
}
