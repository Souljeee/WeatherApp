package com.example.weatherapp.Model

data class WeatherInfo(
    var weather : Weather? = null,
    val main : Main? = null,
    val wind: Wind? = null
)