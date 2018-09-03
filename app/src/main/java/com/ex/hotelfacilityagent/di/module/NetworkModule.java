package com.ex.hotelfacilityagent.di.module;

import com.ex.hotelfacilityagent.network.FacilityApiService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module
public class NetworkModule {

    @Singleton
    @Provides
    String provideBaseUrl() {
        return "https://my-json-server.typicode.com/";
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    FacilityApiService provideSoundApiService(Retrofit retrofit) {
        return retrofit.create(FacilityApiService.class);
    }


}
