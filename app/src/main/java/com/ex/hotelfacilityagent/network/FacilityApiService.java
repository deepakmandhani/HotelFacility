package com.ex.hotelfacilityagent.network;

import com.ex.hotelfacilityagent.model.FacilityApiResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FacilityApiService {

    @GET("iranjith4/ad-assignment/db/")
    Observable<FacilityApiResponse> getFacilityResponse();
}
