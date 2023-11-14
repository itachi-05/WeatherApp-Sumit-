package com.powerhouseweatherai.sumit.domain.models

data class Main(
    val feels_like: Double?,
    val grnd_level: Long?,
    val humidity: Long?,
    val pressure: Long?,
    val sea_level: Long?,
    val temp: Double?,
    val temp_max: Double?,
    val temp_min: Double?
)