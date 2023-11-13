package com.powerhouseweatherai.sumit.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powerhouseweatherai.sumit.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    fun getWeatherData(lat: String, lon: String, appId: String) {
        viewModelScope.launch {
            weatherRepository.getWeatherData(lat, lon, appId)
        }
    }
}