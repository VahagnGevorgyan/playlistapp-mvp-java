package com.playlistapp.ui.offline;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.playlistapp.R;
import com.playlistapp.ui.view.PopupActionButton;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * Shows when network state was lost.
 */
public class OfflineDialog {

    @BindView(R.id.popup_buttons_container)
    LinearLayout mButtonsContainer;

    @BindString(R.string.popup_offline_retry)
    String mButtonLabel;

    private AppCompatActivity mActivity;
    private Runnable mRetryRunnable;
    private AlertDialog mAlertDialog;
    private Unbinder mUnBinder;

    private static boolean isShown = false;

    public static boolean alreadyOnTheScreen() {
        return isShown;
    }

    /**
     * Setting up OfflineDialog popup with retry button.
     *
     */
    private OfflineDialog(AppCompatActivity activity, Runnable retryRunnable) {
        Timber.d("Setting up \"OfflineDialog\" popup");
        mActivity = activity;
        mRetryRunnable = retryRunnable;

        validatePopupInputs();
        createDialog();
    }

    /**
     * Showing "No Internet connection" popup.
     * Will Retry operation, or go to the off-line content.
     *
     * @param buttonRetryRunnable What to do to Retry failed operation.
     */
    public static void showNoInternetPopup(@NonNull AppCompatActivity activity, Runnable buttonRetryRunnable) {
        Timber.d("Trying to show \"No Internet connection\" popup");

        if (activity.isFinishing()) {
            Timber.d("Will not show \"No Internet connection\" popup, because Activity is finishing");
            return;
        }

        Timber.d("Will show popup in Activity: " + activity.getClass().getSimpleName());

        OfflineDialog noInternetPopup = new OfflineDialog(activity,
                buttonRetryRunnable);

        try {
            Timber.d("Showing \"No Internet connection\" popup");
            noInternetPopup.show();
        } catch (IllegalStateException ignore) { // NOSONAR
            Timber.d("Not showing popup if saveInstanceState is called");
        }
    }

    /**
     * Showing popup.
     */
    public void show() {
        Timber.d("Showing popup");
        mAlertDialog.show();
    }

    /**
     * Creating popup.
     */
    private void createDialog() {
        Timber.d("Creating Green Popup");

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

        View rootView = getRootView(LayoutInflater.from(mActivity));
        if (rootView != null) {
            builder.setView(rootView);
        }

        mAlertDialog = builder.create();
        mAlertDialog.setCanceledOnTouchOutside(false);

        customizeOfflineDialog();
        isShown = true;
    }

    /**
     * Layout for popup.
     */
    private View getRootView(LayoutInflater inflater) {
        Timber.d("Inflating Popup view and binding variables to it");
        View view;
        view = inflater.inflate(R.layout.dialog_offline, null, false);
        mUnBinder = ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Validating popup parameters.
     */
    private void validatePopupInputs() {
        Timber.d("Validating popup parameters");

        if (mRetryRunnable == null) {
            throw new NullPointerException("mRetryRunnable is null");
        }
    }

    /**
     * Adding buttons to the dialog.
     */
    private void customizeOfflineDialog() {
        Timber.d("Adding Retry button to the popup");
        PopupActionButton retryButton = createActionButton(mButtonLabel, mButtonsContainer);
        retryButton.setOnClickListener(v -> {
            Timber.d("Pressed Retry button, dismissing popup");
            dismissPopup();
            if (mRetryRunnable != null) {
                Timber.d("Executing Retry Runnable");
                mRetryRunnable.run();
            }
        });
    }

    /**
     * Creates action button for popup.
     */
    private PopupActionButton createActionButton(String buttonTitle, ViewGroup buttonLayout) {
        Timber.d("Creating Popup button with title: " + buttonTitle);
        PopupActionButton actionButton = new PopupActionButton(mActivity);
        actionButton.setTitle(buttonTitle);
        buttonLayout.addView(actionButton, actionButton.getLayoutParams());
        return actionButton;
    }

    /**
     * Destroying popup.
     */
    private void dismissPopup() {
        Timber.d("Destroying popup");
        mAlertDialog.dismiss();
        mUnBinder.unbind();
        isShown = false;
    }
}