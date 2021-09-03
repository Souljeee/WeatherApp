package com.example.weatherapp.View;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.LifecycleOwner;

import com.example.weatherapp.AppState;
import com.example.weatherapp.Model.WeatherInfo;
import com.example.weatherapp.ViewModel.WeatherViewModel;
import com.example.weatherapp.databinding.WeatherFragmentBinding;

import org.jetbrains.annotations.NotNull;


public class WeatherFragment extends Fragment {

    private WeatherViewModel mViewModel;
    private WeatherFragmentBinding binding;
    private String cityName;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = WeatherFragmentBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            cityName = bundle.getString("CITY_NAME");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(WeatherViewModel.class);
        mViewModel.getData().observe((LifecycleOwner) getViewLifecycleOwner(), new Observer<AppState>() {
            @Override
            public void onChanged(AppState appState) {
                renderData(appState);
            }
        });
        mViewModel.getDataFromServer(cityName);
    }

    private void renderData(AppState appState){
        if(appState instanceof AppState.Success){
            Log.d("tag","салам тут все норм");
            WeatherInfo wi = ((AppState.Success) appState).getServerResponseData();
            show(wi);
        }
        if(appState instanceof AppState.Loading){
            Log.d("tag","загружаюсь жес");
        }
        if(appState instanceof AppState.Error){
            Log.d("tag","ебать ты баран");
            Log.d("tag",((AppState.Error) appState).getError().getMessage());
        }
    }

    private void show(WeatherInfo wi) {
        binding.description.setText(wi.getWeather().get(0).getDescription().toString());
        binding.temp.setText(String.valueOf(wi.getMain().getTemp()));
        binding.feelsLike.setText(String.valueOf(wi.getMain().getFeels_like()));
        binding.minTemp.setText(String.valueOf(wi.getMain().getTemp_min()));
        binding.maxTemp.setText(String.valueOf(wi.getMain().getTemp_max()));
        binding.windSpeed.setText(String.valueOf(wi.getWind().getSpeed()));
    }


    public static WeatherFragment newInstance(String cityName) {
        WeatherFragment f = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putString("CITY_NAME",cityName);
        f.setArguments(bundle);
        return f;
    }
}