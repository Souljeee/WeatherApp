package com.example.weatherapp.Model.Repository

interface Repository {
    fun getWeatherFromServer() : String
}