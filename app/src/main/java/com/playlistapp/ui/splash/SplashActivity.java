package com.playlistapp.ui.splash;

import android.os.Handler;

import com.playlistapp.R;
import com.playlistapp.ui.base.ActivityCode;
import com.playlistapp.ui.base.BaseActivity;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Splash screen activity class.
 */
public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    private final Handler mTimerHandler = new Handler();

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {
        Timber.d("Injecting \"Splash\" activity");
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViews() {
        Timber.d("Trying to initialize view elements");
        mPresenter.onAttach(SplashActivity.this);
    }

    @Override
    public void onSplashAttached(int timeOut) {
        Timber.d("SplashMvpPresenter is attached");
        mTimerHandler.postDelayed(() -> {
            launchActivity(ActivityCode.HOME);
            overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        }, timeOut);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
