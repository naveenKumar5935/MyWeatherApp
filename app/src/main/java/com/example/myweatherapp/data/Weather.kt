package com.example.myweatherapp.data

import com.example.myweatherapp.WeatherType

data class Weather(
    val hourList: List<Hour>,
    val weatherType: WeatherType
)
