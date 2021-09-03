package com.example.weatherapp.ViewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.AppState
import com.example.weatherapp.Model.API.WeatherAPIImpl
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class WeatherViewModel(
    private val retrofitImpl: WeatherAPIImpl = WeatherAPIImpl()
) : ViewModel(), CoroutineScope by MainScope() {

    private val liveDataForViewToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getData(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    fun getDataFromServer(cityName : String) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        val apiKey = "3de78552f2c15e641fa7316e79d694cc"
        val units = "metric"
        launch{
            val job = async(Dispatchers.IO) { retrofitImpl.getRetrofitImpl().getWeather(cityName,apiKey,units).execute().body() }
            liveDataForViewToObserve.value = AppState.Success(job.await()!!)
        }
    }


}