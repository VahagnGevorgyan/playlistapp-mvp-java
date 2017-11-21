package com.playlistapp.ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.playlistapp.App;
import com.playlistapp.R;
import com.playlistapp.di.component.ActivityComponent;
import com.playlistapp.di.component.DaggerActivityComponent;
import com.playlistapp.di.module.ActivityModule;
import com.playlistapp.ui.home.HomeActivity;
import com.playlistapp.utils.CommonUtils;
import com.playlistapp.utils.FullscreenUtils;
import com.playlistapp.utils.network.NetworkStateHelper;
import com.playlistapp.utils.network.NetworkStateManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.view.View.VISIBLE;


/**
 * Base class for all activities.
 */
public abstract class BaseActivity extends EventBusActivity
        implements MvpView, BaseFragment.Callback {

    @Inject
    NetworkStateHelper mNetworkStateHelper;

    /**
     * Layout container for using some cases.
     */
    @Nullable
    @BindView(R.id.layout_container)
    View mContainer;

    /**
     * Progress bar for using local loading.
     */
    @Nullable
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;



    /**
     * Bind layout file.
     *
     * @return layout file id
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * Inject Dagger components.
     */
    protected abstract void initInjector();

    /**
     * Initializing layout views.
     */
    protected abstract void initViews();

    /**
     * ButterKnife binder.
     */
    private Unbinder mUnBinder;

    private ProgressDialog mProgressDialog;

    private ActivityComponent mActivityComponent;

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareWindow();
        setContentView(attachLayoutRes());

        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(App.getAppComponent())
                .build();

        mUnBinder = ButterKnife.bind(this);
        initInjector();
        initViews();
        prepareLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initializeNetworkStateManager();
    }

    protected void prepareWindow() {
        Timber.d("Preparing app window adding flags");
        FullscreenUtils.setWindowUiVisibility(getWindow());
    }

    protected void prepareLayout() {
        Timber.d("Preparing activity layout and set sizes, if software buttons exists on screen");
        if (mContainer != null) {
            mContainer.setPadding(mContainer.getPaddingLeft(),
                    mContainer.getPaddingTop() + FullscreenUtils.getStatusBarHeight(this, false),
                    mContainer.getPaddingRight(),
                    FullscreenUtils.getNavigationBarSize(this).y + mContainer.getPaddingBottom());
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showProgressBar() {
        hideProgressBar();
        if (mProgressBar != null) {
            mProgressBar.setVisibility(VISIBLE);
        }
    }

    @Override
    public void hideProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    private void initializeNetworkStateManager() {
        Timber.d("Tyring to initialize \"NetworkStateManager\"");

        if (mNetworkStateHelper != null) {
            mNetworkStateHelper.initializeNetworkStatusListener();
        }
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkStateManager.isConnected(getApplicationContext());
    }

    @Override
    public void onFragmentAttached() {
        Timber.d("Fragment attached");
    }

    @Override
    public void onFragmentDetached(String tag) {
        Timber.d("Fragment detached");
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @Override
    protected void onDestroy() {
        mUnBinder.unbind();
        super.onDestroy();
    }

    protected  void launchActivity(@NonNull ActivityCode code) {
        launchActivity(code, null);
    }

    protected void launchActivity(@NonNull ActivityCode code, @Nullable Integer flags) {
        switch (code) {
//            case ONBOARDING:
//                launchOnBoardingActivity(flags);
//                break;
//            case REGISTER_SAVE:
//                launchRegisterSaveActivity();
//                break;
//            case REGISTER:
//                launchRegisterActivity();
//                break;
//            case LOGIN:
//                launchLoginActivity();
//                break;
            case HOME:
            default:
                launchMainActivity();
                break;
        }
    }

    private void launchMainActivity() {
        launchActivity(HomeActivity.class, Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        finish();
    }

    // TODO : Open after creating activities
//    private void launchOnBoardingActivity() {
//        launchActivity(OnBoardingActivity.class);
//        finish();
//    }
//
//    private void launchOnBoardingActivity(@Nullable Integer flags) {
//        if(flags != null) {
//            launchActivity(OnBoardingActivity.class, flags);
//            finish();
//        } else {
//            launchOnBoardingActivity();
//        }
//    }

    private void launchActivity(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    private void launchActivity(Class<? extends Activity> clazz, int flags) {
        Intent intent = new Intent(this, clazz);
        intent.addFlags(flags);
        startActivity(intent);
    }
}
