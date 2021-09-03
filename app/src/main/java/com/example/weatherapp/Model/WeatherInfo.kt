package com.example.weatherapp.Model

data class WeatherInfo(
    var weather: ArrayList<Weather> = arrayListOf<Weather>(),
    val main : Main? = null,
    val wind: Wind? = null
)