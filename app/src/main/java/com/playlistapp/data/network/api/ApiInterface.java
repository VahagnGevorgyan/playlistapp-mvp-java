package com.playlistapp.data.network.api;


import com.playlistapp.data.network.data.track.TrackResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Web services calls for Retrofit definition.
 */
public interface ApiInterface {

    /**
     * Calls TrackList request.
     */
    @GET
    Observable<Response<TrackResponse>> callTrackListApi(@Url String requestUrl,
                                                         @Query("country") String country,
                                                         @Query("limit") Integer limit,
                                                         @Query("page") Integer page
    );

}
