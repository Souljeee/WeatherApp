package com.example.weatherapp.Model.API

import com.example.weatherapp.Model.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/weather")
    fun getWeather(
        @Query("q") cityName : String,
        @Query("appid") apiKey : String,
        @Query("units") units : String
    ): Call<WeatherInfo>
}