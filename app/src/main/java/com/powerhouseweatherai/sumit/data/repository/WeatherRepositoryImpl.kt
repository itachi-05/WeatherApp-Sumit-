package com.powerhouseweatherai.sumit.data.repository

import com.powerhouseweatherai.sumit.data.local.WeatherDetailsDao
import com.powerhouseweatherai.sumit.data.mapper.toWeatherDetailEntity
import com.powerhouseweatherai.sumit.data.mapper.toWeatherDetailResponse
import com.powerhouseweatherai.sumit.data.remote.ApiServices
import com.powerhouseweatherai.sumit.domain.models.WeatherDetailResponse
import com.powerhouseweatherai.sumit.domain.repository.WeatherRepository
import com.powerhouseweatherai.sumit.responsehandler.APIResponse
import com.powerhouseweatherai.sumit.responsehandler.ResponseHandler
import com.powerhouseweatherai.sumit.responsehandler.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val responseHandler: ResponseHandler,
    private val weatherDetailsDao: WeatherDetailsDao
) : WeatherRepository {


    override suspend fun getWeatherData(
        lat: String,
        lon: String,
        appId: String,
        fromServer: Boolean
    ): APIResponse<WeatherDetailResponse?> {

        if(!fromServer){
            val weatherDetailEntity = weatherDetailsDao.getWeatherDetail(lat+lon)
            if(weatherDetailEntity != null){
                return APIResponse.Success(weatherDetailEntity.toWeatherDetailResponse())
            }
        }

        val result = responseHandler.callAPI {
            apiServices.getWeather(lat, lon, appId)
        }
        if (result is APIResponse.Success) {
            weatherDetailsDao.insert(result.data?.toWeatherDetailEntity()!!)
        }

        return result.map {
            it.toWeatherDetailResponse()
        }
    }


}