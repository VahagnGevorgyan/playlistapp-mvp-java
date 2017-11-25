package com.playlistapp.di.api;


import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.playlistapp.BuildConfig;
import com.playlistapp.data.network.api.ApiHelper;
import com.playlistapp.data.network.api.ApiInterface;
import com.playlistapp.data.network.session.Session;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ApiModule {

    private final Application mApp;

    public ApiModule(Application application) {
        mApp = application;
    }

    @Provides
    @Singleton
    ApiHelper provideData(ApiInterface apiInterface, Session session, @ApiUrl HttpUrl baseUrl) {
        return new ApiHelper(apiInterface, session, baseUrl);
    }

    @Provides
    @Singleton
    Session provideSession(@ApiUrl HttpUrl baseUrl, @ApiInfo String apiKey) {
        return new Session(baseUrl.toString(), apiKey);
    }

    @Provides
    ApiInterface provideApiService(final Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(
            @ApiUrl final HttpUrl baseUrl,
            @ApiHttpClient final OkHttpClient client,
            final Gson gson) {

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @ApiUrl
    HttpUrl provideApiUrl() {
        return HttpUrl.parse(BuildConfig.BASE_URL);
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return interceptor;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
//                .registerTypeAdapter(TrackResponse.class, new TrackFeedDeserializer()) // TODO: For Test - Remove after test
                .setLenient()
                .create();
    }

    @Provides
    @Singleton
    @ApiHttpClient
    OkHttpClient provideHttpClient(final HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor).build();
    }
}
