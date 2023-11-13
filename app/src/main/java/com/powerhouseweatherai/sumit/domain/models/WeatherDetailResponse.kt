package com.powerhouseweatherai.sumit.domain.models

data class WeatherDetailResponse(
    val base: String?,
    val cod: Int?,
    val coord: Coord?,
    val main: Main?,
    val name: String?,
    val sys: Sys?,
    val timezone: Int?,
    val visibility: Int?,
    val weather: List<Weather?>?,
    val wind: Wind?
)