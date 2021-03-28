package com.example.currencyonverter.retrofit;

import com.example.currencyonverter.model.Object;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("latest.js")
    Call<Object> someResponse();
}
