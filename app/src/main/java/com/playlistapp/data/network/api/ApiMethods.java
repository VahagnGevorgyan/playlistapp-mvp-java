package com.playlistapp.data.network.api;


import com.playlistapp.data.network.data.ApiResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Various data processing and validation methods for Rx chains.
 */
public class ApiMethods {

    /**
     * Proceed with default validation chain.
     *
     * @param <T>
     * @return
     */
    public static <T> Function<Response<T>, Observable<T>> validate() {
        return result -> Observable.just(result)
                .flatMap(checkForRetrofitError())
                .flatMap(checkResponseForKOError())
                .flatMap(validateJSON());
    }

    /**
     * Validate returned data object.
     *
     * @param <T>
     * @return
     */
    public static <T> Function<Response<T>, ObservableSource<T>> validateJSON() {
        return result -> {
            Timber.d("Validating parsed data object values");
            if (result == null) {
                return Observable.error(new Throwable("ApiResponse was null"));
            }
            return Observable.just(result.body());
        };
    }

    /**
     * Check for error being returned by Retrofit.
     *
     * @param <T>
     * @return
     */
    public static <T> Function<Response<T>, ObservableSource<Response<T>>> checkForRetrofitError() {
        return result -> {
            Timber.d("Checking web service response for Retrofit error presence");
            if (!result.isSuccessful()) {
                if (result.errorBody() != null) {
                    Timber.e(result.errorBody().toString());
                    return Observable.error(new Throwable(result.errorBody().toString()));
                }
                return Observable.error(new Throwable("Retrofit error is being present"));
            } else {
                if (result.body() != null) {
                    Timber.d("No Retrofit error is being present");
                    return Observable.just(result);
                } else {
                    Timber.e("Web service response body is null");
                    if (result.errorBody() != null) {
                        return Observable.error(new Throwable(result.errorBody().toString()));
                    }
                    return Observable.error(new Throwable("Web service response body is null"));
                }
            }
        };
    }

    /**
     * Check if web service returned KO response.
     *
     * @param <T> must extend ApiResponse.
     * @return
     */
    public static <T> Function<Response<T>, ObservableSource<Response<T>>> checkResponseForKOError() {
        return result -> {
            Timber.d("Checking web service response for JSON \"KO\" error presence");

            ApiResponse res = (ApiResponse) result.body();
            if (res == null || !res.isSuccessful() || !res.isOk()) {
                Timber.d("JSON \"KO\" error is being present");
                return Observable.error(res);
            }

            Timber.d("No JSON \"KO\" error is being present");

            return Observable.just(result);
        };
    }
}
