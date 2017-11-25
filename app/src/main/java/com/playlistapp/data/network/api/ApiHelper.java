package com.playlistapp.data.network.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.playlistapp.data.network.data.track.TrackResData;
import com.playlistapp.data.network.data.track.TrackResponse;
import com.playlistapp.data.network.session.Session;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.HttpUrl;
import timber.log.Timber;

/**
 * Provides data for the app.
 */
@Singleton
public class ApiHelper {

    private ApiInterface mApiService;
    private Session mSession;
    private String mBaseUrl;

    /**
     * Constructor.
     *
     * @param apiService
     * @param session
     * @param baseUrl
     */
    @Inject
    public ApiHelper(ApiInterface apiService,
                     Session session,
                     HttpUrl baseUrl
    ) {
        mApiService = apiService;
        mSession = session;
        mBaseUrl = baseUrl.toString();
    }

    /**
     * Returns base url for web services calls.
     *
     * @return
     */
    public String getBaseUrl() {
        return mBaseUrl;
    }

    /**
     * Returns Tracks response.
     *
     * @return
     */
    @NonNull
    public Observable<TrackResData> doTrackListCall(@NonNull String country,
                                                    @Nullable Integer limit) {
        String url = mSession.getTrackListServiceURL();
        Timber.d("Requesting Track List web service with url: " + url);

        return mApiService.callTrackListApi(url, country, limit)
                .flatMap(ApiMethods.validate())
                .flatMap(processTrackListResponse());
    }



    /*****************************************/
    // DIFFERENT WAYS OF PROCESSING RESULT

    /**
     * Processing received Tracks List Response data.
     *
     * @return
     */
    private Function<TrackResponse, ObservableSource<TrackResData>> processTrackListResponse() {
        Timber.d("Processing received Track List Response data");
        return result -> Observable.just(result.getData());
    }

}
