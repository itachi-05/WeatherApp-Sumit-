package com.powerhouseweatherai.sumit.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.powerhouseweatherai.sumit.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
    }
}