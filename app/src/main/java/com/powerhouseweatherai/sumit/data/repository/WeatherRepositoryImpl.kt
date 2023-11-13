package com.powerhouseweatherai.sumit.data.repository

import com.powerhouseweatherai.sumit.data.mapper.toWeatherDetailResponse
import com.powerhouseweatherai.sumit.data.remote.ApiServices
import com.powerhouseweatherai.sumit.domain.models.WeatherDetailResponse
import com.powerhouseweatherai.sumit.domain.repository.WeatherRepository
import com.powerhouseweatherai.sumit.responsehandler.APIResponse
import com.powerhouseweatherai.sumit.responsehandler.ResponseHandler
import com.powerhouseweatherai.sumit.responsehandler.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices
) : WeatherRepository {

    @Inject
    private lateinit var responseHandler: ResponseHandler

    override suspend fun getWeatherData(
        lat: String,
        lon: String,
        appId: String
    ): APIResponse<WeatherDetailResponse?> {
        val result = responseHandler.callAPI {
            apiServices.getWeather(lat, lon, appId)
        }

        return result.map {
            it.toWeatherDetailResponse()
        }
    }


}