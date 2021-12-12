package com.example.medic_server.Repository.Network.ProfanityLogic;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PurgoMalumAPI {
    @GET("json")
    @Headers({
            "Accept: application/json"
    })
    Call<ProfanityChecker.ProfanityResponse> listAddresses(
            @Query("text") String text,
            @Header("x-rapidapi-host") String host,
            @Header("x-rapidapi-key") String token);
}
