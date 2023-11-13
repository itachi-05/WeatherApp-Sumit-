package com.powerhouseweatherai.sumit.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powerhouseweatherai.sumit.domain.models.WeatherDetailResponse
import com.powerhouseweatherai.sumit.domain.repository.WeatherRepository
import com.powerhouseweatherai.sumit.responsehandler.APIResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherData = MutableLiveData<APIResponse<WeatherDetailResponse>>()
    val weatherData: LiveData<APIResponse<WeatherDetailResponse>>
        get() = _weatherData

    fun getWeatherData(lat: String, lon: String, appId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherData.postValue(APIResponse.Loading())
            _weatherData.postValue(
                weatherRepository.getWeatherData(lat, lon, appId)
            )
        }
    }
}