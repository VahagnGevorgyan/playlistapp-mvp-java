package com.playlistapp.data.network.api;

import android.support.annotation.NonNull;


import com.playlistapp.data.network.session.Session;

import java.util.List;

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

//    /**
//     * Returns Login response.
//     *
//     * @return
//     */
//    @NonNull
//    public Observable<LoginData> doLoginCall(LoginRequest request) {
//        String url = mSession.getLoginServiceURL();
//        Timber.d("Requesting Login web service with url: " + url);
//
//        return mApiService.callLoginApi(url, request)
//                .flatMap(ApiMethods.validate())
//                .flatMap(processLoginResponse());
//    }

//    /**
//     * Returns Cities list.
//     *
//     * @return
//     */
//    @NonNull
//    public Observable<List<CityItem>> getCitiesList() {
//        String url = mSession.getCitiesListServiceURL();
//        Timber.d("Requesting from the web service Cities List data: " + url);
//
//        return mApiService.callCitiesListApi(url, new Object())
//                .flatMap(ApiMethods.validate())
//                .flatMap(processCitiesListData());
//    }



    /*****************************************/
    // DIFFERENT WAYS OF PROCESSING RESULT

//    /**
//     * Processing received Login Response data.
//     *
//     * @return
//     */
//    private Function<LoginResponse, ObservableSource<LoginData>> processLoginResponse() {
//        Timber.d("Processing received Login Response data");
//        return result -> Observable.just(result.getData());
//    }

//    /**
//     * Processing received Cities List data.
//     *
//     * @return
//     */
//    private Function<CityListApiResponse, ObservableSource<List<CityItem>>> processCitiesListData() {
//        Timber.d("Processing received Cities List data");
//        return result -> Observable.just(result.getData());
//    }

}
