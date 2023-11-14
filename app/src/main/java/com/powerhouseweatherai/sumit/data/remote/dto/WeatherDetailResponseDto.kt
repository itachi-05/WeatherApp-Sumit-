package com.powerhouseweatherai.sumit.data.remote.dto

data class WeatherDetailResponseDto(
    val base: String?,
    val clouds: Clouds?,
    val cod: Long?,
    val coord: Coord?,
    val dt: Long?,
    val id: Long?,
    val main: Main?,
    val name: String?,
    val sys: Sys?,
    val timezone: Long?,
    val visibility: Long?,
    val weather: List<Weather?>?,
    val wind: Wind?
)