package com.example.myweatherapp.data

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// http://api.weatherapi.com/v1/current.json?key=01dee5675df54ad8bb600106241803&q=London
interface ApiInterface {

    @GET("forecast.json")
    suspend fun getWeatherData(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("days") days: Int
    ): WeatherData
}