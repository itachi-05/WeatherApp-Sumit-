package com.powerhouseweatherai.sumit.data.repository

import android.content.Context
import com.powerhouseweatherai.sumit.R
import com.powerhouseweatherai.sumit.data.local.WeatherDetailsDao
import com.powerhouseweatherai.sumit.data.mapper.toWeatherDetailEntity
import com.powerhouseweatherai.sumit.data.mapper.toWeatherDetailResponse
import com.powerhouseweatherai.sumit.data.remote.ApiServices
import com.powerhouseweatherai.sumit.data.remote.dto.WeatherDetailResponseDto
import com.powerhouseweatherai.sumit.domain.models.WeatherDetailResponse
import com.powerhouseweatherai.sumit.domain.repository.WeatherRepository
import com.powerhouseweatherai.sumit.responsehandler.APIResponse
import com.powerhouseweatherai.sumit.responsehandler.ResponseHandler
import com.powerhouseweatherai.sumit.responsehandler.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val responseHandler: ResponseHandler,
    private val weatherDetailsDao: WeatherDetailsDao,
    private val context: Context
) : WeatherRepository {


    override suspend fun getWeatherData(
        lat: String,
        lon: String,
        appId: String,
        fromServer: Boolean
    ): APIResponse<WeatherDetailResponse?> {

        val result: APIResponse<WeatherDetailResponseDto>
        if (!fromServer) {
            val weatherDetailEntity = weatherDetailsDao.getWeatherDetail(lat + lon)
            if (weatherDetailEntity != null) {
                return APIResponse.Success(weatherDetailEntity.toWeatherDetailResponse())
            }
        } else {

            result = responseHandler.callAPI {
                apiServices.getWeather(lat, lon, appId)
            }
            if (result is APIResponse.Success) {
                weatherDetailsDao.insert(result.data?.toWeatherDetailEntity()!!)
            }

            return result.map {
                it.toWeatherDetailResponse()
            }
        }
        return APIResponse.Error(context.getString(R.string.no_data_found))
    }


}