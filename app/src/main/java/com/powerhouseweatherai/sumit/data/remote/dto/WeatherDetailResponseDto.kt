package com.powerhouseweatherai.sumit.data.remote.dto

data class WeatherDetailResponseDto(
    val coord: Coord?,
    val dt: Long?,
    val id: Long?,
    val main: Main?,
    val name: String?,
    val visibility: Long?,
    val weather: List<Weather?>?,

)