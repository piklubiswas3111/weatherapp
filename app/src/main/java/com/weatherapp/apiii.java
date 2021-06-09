package com.weatherapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiii {
    @GET("weather")
    Call<Example> getwheater(
            @Query("q") String city,
            @Query("appid") String apii
    );
}
