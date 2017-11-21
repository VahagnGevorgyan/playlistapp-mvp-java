package com.playlistapp.utils.network;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.playlistapp.Constants;
import com.playlistapp.ui.offline.OfflineDialog;
import com.playlistapp.ui.splash.SplashActivity;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import timber.log.Timber;

import static com.playlistapp.utils.network.NetworkStateManager.NetworkType.MOBILE;
import static com.playlistapp.utils.network.NetworkStateManager.NetworkType.NOT_CONNECTED;
import static com.playlistapp.utils.network.NetworkStateManager.NetworkType.WIFI;


/**
 * Utils for networking stuff - check status etc.
 */
public class NetworkStateManager implements NetworkStateHelper {

    private final AppCompatActivity mActivity;

    private Boolean mInitializedNetworkMonitoring = false;
    private boolean mIsConnectedToInternet;
    private int mNetworkType;
    private final Object mInitializedNetworkMonitoringLock = new Object();
    private final Object mConnectedLock = new Object();
    private final BehaviorSubject<Boolean> mInitializedNetworkObservable = BehaviorSubject.create();
    private final BehaviorSubject<Boolean> mIsInternetAvailableObservable = BehaviorSubject.create();

    @Inject
    public NetworkStateManager(AppCompatActivity activity) {
        mActivity = activity;
    }

    /**
     * Returns current network connectivity status.
     *
     * @param context
     * @return
     */
    public static NetworkType getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            switch (activeNetwork.getType()) {
                case ConnectivityManager.TYPE_WIFI:
                    return WIFI;
                case ConnectivityManager.TYPE_MOBILE:
                    return MOBILE;
            }
        }
        return NOT_CONNECTED;
    }

    /**
     * Tells if the device is connected to some network.
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {
        return getConnectivityStatus(context) != NOT_CONNECTED;
    }

    public int getNetworkType() {
        return mNetworkType;
    }

    /**
     * Network types.
     */
    public enum NetworkType {
        WIFI("WIFI"), MOBILE("MOBILE"), NOT_CONNECTED("NOT CONNECTED");

        private String name;

        NetworkType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Starts Network State monitoring.
     */
    public void initializeNetworkStatusListener() {
        Timber.d("Starts Network State monitoring");
        ReactiveNetwork.observeNetworkConnectivity(mActivity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connectivity -> {
                            NetworkInfo.State connectivityState = connectivity.getState();
                            boolean isConnected = connectivityState == NetworkInfo.State.CONNECTED;
                            Timber.d("Internet connectivity status changed to: " + connectivity.toString());
                            Timber.d("Interpreted it as: is connected? " + isConnected);
                            setConnectedToInternet(isConnected, connectivity.getType());
                            if (!isNetworkMonitoringInitialized()) {
                                setNetworkMonitoringInitialized(true);
                            }
                            recheckNetwork();
                        },
                        throwable -> {
                            Timber.e(throwable.getMessage());
                            if (!isNetworkMonitoringInitialized()) {
                                retryInitializingNetworkStatusListener();
                            }
                        });
    }

    /**
     * Checks if has to show "No Internet" popup.
     */
    @Override
    public void recheckNetwork() {
        Timber.d("Checks if has to show \"No Internet\" popup");
        if (!isConnectedToInternet()
                && mActivity != null
                && !(mActivity instanceof SplashActivity)
                && !OfflineDialog.alreadyOnTheScreen()
                ){
            Timber.d("Showing \"No Internet\" popup");

            OfflineDialog.showNoInternetPopup(mActivity, this::recheckNetwork);
        }
    }

    /**
     * Retrying to initialize network state monitoring.
     */
    public void retryInitializingNetworkStatusListener() {
        Timber.d("Will retry initialization of network state monitoring after " + Constants.TIMEOUT_RETRY_INITIALIZING_NETWORK_LISTENER + " ms");
        new Handler().postDelayed(this::initializeNetworkStatusListener, Constants.TIMEOUT_RETRY_INITIALIZING_NETWORK_LISTENER);
    }

    /**
     * Tells if the network connectivity monitoring has been initialized.
     *
     * @return
     */
    public boolean isNetworkMonitoringInitialized() {
        synchronized (mInitializedNetworkMonitoringLock) {
            return mInitializedNetworkMonitoring;
        }
    }

    /**
     * Sets network connectivity monitoring initialization status.
     *
     * @param initializedNetworkMonitoring
     */
    public void setNetworkMonitoringInitialized(boolean initializedNetworkMonitoring) {
        synchronized (mInitializedNetworkMonitoringLock) {
            Timber.d("Sets network connectivity monitoring initialization status to: " + initializedNetworkMonitoring);
            mInitializedNetworkMonitoring = initializedNetworkMonitoring;
            mInitializedNetworkObservable.onNext(initializedNetworkMonitoring);
        }
    }

    /**
     * Returns network connectivity monitoring initialization status Observable.
     *
     * @return
     */
    public Observable<Boolean> getIsNetworkMonitoringInitializedObservable() {
        return mInitializedNetworkObservable;
    }

    /**
     * Returns Internet availability monitoring Observable.
     *
     * @return
     */
    public Observable<Boolean> getIsInternetAvailableObservable() {
        return mIsInternetAvailableObservable;
    }

    /**
     * Setting if the device is connected to the Internet.
     *
     * @param connectedToInternet Is the device now connected to the Internet?
     * @param networkType         Current Network Type in form of ConnectivityManager.TYPE_WIFI types.
     */
    public void setConnectedToInternet(boolean connectedToInternet, int networkType) {
        synchronized (mConnectedLock) {
            Timber.d("Setting if the device is connected to the Internet status to: " + connectedToInternet);
            mIsConnectedToInternet = connectedToInternet;
            mNetworkType = networkType;
            mIsInternetAvailableObservable.onNext(connectedToInternet);
        }
    }

    /**
     * Tells if the device currently is connected to the Internet.
     *
     * @return Is the device currently connected to the Internet?
     */
    public boolean isConnectedToInternet() {
        synchronized (mConnectedLock) {
            Timber.d("Is the device connected to the Internet? " + mIsConnectedToInternet);
            return mIsConnectedToInternet;
        }
    }

    public void displayErrorInternal(Context context, String msg) {
        Timber.d("Trying to show Alert dialog");

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error")
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Ok", (dialog, id) -> dialog.cancel());
        final AlertDialog alert = builder.create();

        alert.show();
    }


}


