package com.powerhouseweatherai.sumit.domain.models



data class WeatherDetailResponse(
    val coord: Coord?,
    val dt: Long?,
    val id: Long?,
    val main: Main?,
    val name: String?,
    val visibility: Long?,
    val weather: List<Weather?>?,
)