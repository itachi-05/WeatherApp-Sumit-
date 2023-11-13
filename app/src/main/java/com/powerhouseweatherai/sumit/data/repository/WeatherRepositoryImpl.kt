package com.powerhouseweatherai.sumit.data.repository

import com.powerhouseweatherai.sumit.data.remote.ApiServices
import com.powerhouseweatherai.sumit.domain.models.WeatherDetailResponse
import com.powerhouseweatherai.sumit.domain.repository.WeatherRepository
import com.powerhouseweatherai.sumit.responsehandler.APIResponse
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : WeatherRepository {
    override suspend fun getWeatherData(
        lat: String,
        lon: String,
        appId: String
    ): APIResponse<WeatherDetailResponse> {
        TODO("Not yet implemented")
    }
}