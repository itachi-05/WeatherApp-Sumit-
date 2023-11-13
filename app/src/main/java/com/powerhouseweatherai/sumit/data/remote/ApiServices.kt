package com.powerhouseweatherai.sumit.data.remote

import com.powerhouseweatherai.sumit.data.remote.dto.WeatherDetailResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String
    ): Response<WeatherDetailResponseDto>


}