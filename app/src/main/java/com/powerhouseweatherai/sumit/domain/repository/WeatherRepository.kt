package com.powerhouseweatherai.sumit.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.powerhouseweatherai.sumit.domain.models.WeatherDetailResponse
import com.powerhouseweatherai.sumit.responsehandler.APIResponse

interface WeatherRepository {
   suspend fun getWeatherData(lat: String, lon: String, appId: String):APIResponse<WeatherDetailResponse?>
}