package com.example.weatherapp.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputLayout.setEndIconOnClickListener {
            val cityName = binding.inputEditText.text.toString()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container,WeatherFragment.newInstance(cityName))
                .commit()
        }
    }
}