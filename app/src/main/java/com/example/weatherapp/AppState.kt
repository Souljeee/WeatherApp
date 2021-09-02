package com.example.weatherapp

import com.example.weatherapp.Model.WeatherInfo

sealed class AppState {
    data class Success(val serverResponseData: WeatherInfo) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}