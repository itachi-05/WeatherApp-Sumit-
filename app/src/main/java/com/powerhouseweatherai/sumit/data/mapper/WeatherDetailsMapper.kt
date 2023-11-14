package com.powerhouseweatherai.sumit.data.mapper

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.powerhouseweatherai.sumit.data.local.WeatherDetailEntity
import com.powerhouseweatherai.sumit.data.remote.dto.Coord
import com.powerhouseweatherai.sumit.data.remote.dto.Main
import com.powerhouseweatherai.sumit.data.remote.dto.Weather
import com.powerhouseweatherai.sumit.data.remote.dto.WeatherDetailResponseDto
import com.powerhouseweatherai.sumit.domain.models.WeatherDetailResponse


fun WeatherDetailResponseDto.toWeatherDetailResponse() = WeatherDetailResponse(
    coord = coord?.toCoord(),
    main = main?.toMain(),
    name = name,
    visibility = visibility,
    weather = weather?.map { it?.toWeather() },
    dt = dt,
    id = id

)


private fun Coord.toCoord() = com.powerhouseweatherai.sumit.domain.models.Coord(
    lat = lat,
    lon = lon
)

private fun Main.toMain() = com.powerhouseweatherai.sumit.domain.models.Main(
    temp = temp
)


private fun Weather.toWeather() = com.powerhouseweatherai.sumit.domain.models.Weather(
    description = description,
)

fun WeatherDetailResponseDto.toWeatherDetailEntity() = WeatherDetailEntity(
    id = coord?.lat.toString() + coord?.lon.toString(),
    name = name,
    visibility = visibility,
    coord = coord,
    dt = dt,
    main = main,
    weather = weather
)


