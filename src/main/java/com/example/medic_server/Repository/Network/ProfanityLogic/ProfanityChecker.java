package com.example.medic_server.Repository.Network.ProfanityLogic;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfanityChecker {
    private PurgoMalumAPI api;
    private final String API_KEY = "29bb57a21fmsh971770ea1c6d2a3p14da05jsn492b8dd6c6b5";
    private final String API_HOST = "community-purgomalum.p.rapidapi.com";

    public ProfanityChecker() {

    }

    static class ProfanityRequest{
        String value;

        public ProfanityRequest(String value) {
            this.value = value;
        }
    }

    static class ProfanityResponse{
        String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }
}
