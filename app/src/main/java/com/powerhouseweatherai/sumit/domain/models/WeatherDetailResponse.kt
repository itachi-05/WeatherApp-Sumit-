package com.powerhouseweatherai.sumit.domain.models

data class WeatherDetailResponse(
    val base: String?,
    val cod: Long?,
    val coord: Coord?,
    val main: Main?,
    val name: String?,
    val sys: Sys?,
    val timezone: Long?,
    val visibility: Long?,
    val weather: List<Weather?>?,
    val wind: Wind?
)