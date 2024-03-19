package com.example.myweatherapp.data

import com.example.myweatherapp.WeatherType

data class Weather(
    val hourList: List<Hour>,
    val weatherType: WeatherType,
    val city: String,
    val temp: String,
    val airPressure: String,
    val humidity:String,
    val windSpeed: String
)
