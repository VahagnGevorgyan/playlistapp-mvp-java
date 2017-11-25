package com.playlistapp.data.network.session;


import com.playlistapp.Constants;

import java.net.MalformedURLException;
import java.net.URL;

import timber.log.Timber;

import static com.playlistapp.Constants.ENDPOINT_API_BASE;
import static com.playlistapp.Constants.ENDPOINT_TRACK_LIST;
import static com.playlistapp.Constants.FORMAT_WEB_SERVICE;

/**
 * Application Session.
 * Settings, cached data, etc.
 * When shutting down the app, this data will disappear without a trace. No persistence.
 * Class is able to reconstruct itself, if Android nullifies Singletons.
 */
public class Session {

    private String mTrackListServiceURL;

    private boolean mIsLoggedIn;
    private Object mLock = new Object();


    /**
     * Constructor.
     *
     * @param base - url
     */
    public Session(String base, String apiKey) {
        Timber.d("Creating App Session");


        base = constructBaseUrl(base, apiKey, ENDPOINT_API_BASE);
        mTrackListServiceURL = constructFullUrl(base, ENDPOINT_TRACK_LIST);
    }

    /**
     * Creates a base url for further use.
     */
    public String constructBaseUrl(String base, String apiKey, String endpoint) {
        try {
            // Endpoint is a valid url, we should not modify it.
            // TODO Better way to do this?
            new URL(endpoint);
            return endpoint;
        } catch (MalformedURLException e) {
            // Nothing to do here, move along.
        }

        String fullUrl = base + endpoint;
        return fullUrl
                .replaceAll("//", "/")
                .replace(":/", "://")
                .replace("{api_key}", String.valueOf(apiKey))
                .replace("{format}", FORMAT_WEB_SERVICE);
    }

    /**
     * Creates a full url for further use.
     */
    public String constructFullUrl(String base, String endpoint) {
        try {
            // Endpoint is a valid url, we should not modify it.
            // TODO Better way to do this?
            new URL(endpoint);
            return endpoint;
        } catch (MalformedURLException e) {
            // Nothing to do here, move along.
        }

        String fullUrl = base + endpoint;
        return fullUrl
                .replaceAll("//", "/")
                .replace(":/", "://");
    }

    /**
     * Returns Tracks List Web Service URL value.
     *
     * @return
     */
    public String getTrackListServiceURL() {
        return mTrackListServiceURL;
    }


    /**
     * Tells if the User is logged in.
     *
     * @return
     */
    public boolean isLoggedIn() {
        synchronized (mLock) {
            Timber.d("Is the User logged in? " + mIsLoggedIn);
            return mIsLoggedIn;
        }
    }

    /**
     * Sets if the User is logged in.
     *
     * @param loggedIn
     */
    public void setLoggedIn(boolean loggedIn) {
        synchronized (mLock) {
            Timber.d("Setting User logged in status to " + loggedIn);
            mIsLoggedIn = loggedIn;
        }
    }

}
