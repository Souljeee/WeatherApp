package com.example.weatherapp

import com.example.weatherapp.Model.API.WeatherAPI
import com.example.weatherapp.Model.API.WeatherAPIImpl
import com.example.weatherapp.ViewModel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { WeatherAPIImpl() }
    viewModel { WeatherViewModel(get()) }
}