package com.powerhouseweatherai.sumit.data.mapper

import com.powerhouseweatherai.sumit.data.remote.dto.Coord
import com.powerhouseweatherai.sumit.data.remote.dto.Main
import com.powerhouseweatherai.sumit.data.remote.dto.Sys
import com.powerhouseweatherai.sumit.data.remote.dto.Weather
import com.powerhouseweatherai.sumit.data.remote.dto.WeatherDetailResponseDto
import com.powerhouseweatherai.sumit.data.remote.dto.Wind
import com.powerhouseweatherai.sumit.domain.models.WeatherDetailResponse


fun WeatherDetailResponseDto.toWeatherDetailResponse() = WeatherDetailResponse(
    base = base,
    cod = cod,
    coord = coord?.toCoord(),
    main = main?.toMain(),
    name = name,
    sys = sys?.toSys(),
    timezone = timezone,
    visibility = visibility,
    weather = weather?.map { it?.toWeather() },
    wind = wind?.toWind()
)


private fun Coord.toCoord() = com.powerhouseweatherai.sumit.domain.models.Coord(
    lat = lat,
    lon = lon
)

private fun Main.toMain() = com.powerhouseweatherai.sumit.domain.models.Main(
    feels_like = feels_like,
    humidity = humidity,
    pressure = pressure,
    temp = temp,
    temp_max = temp_max,
    temp_min = temp_min,
    grnd_level = grnd_level,
    sea_level = sea_level
)

private fun Sys.toSys() = com.powerhouseweatherai.sumit.domain.models.Sys(
    country = country,
    id = id,
    sunrise = sunrise,
    sunset = sunset,
    type = type
)

private fun Weather.toWeather() = com.powerhouseweatherai.sumit.domain.models.Weather(
    description = description,
    icon = icon,
    id = id,
    main = main
)

private fun Wind.toWind() = com.powerhouseweatherai.sumit.domain.models.Wind(
    deg = deg,
    gust = gust,
    speed = speed
)