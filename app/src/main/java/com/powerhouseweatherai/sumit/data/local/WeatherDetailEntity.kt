package com.powerhouseweatherai.sumit.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.powerhouseweatherai.sumit.data.remote.dto.Coord
import com.powerhouseweatherai.sumit.data.remote.dto.Main
import com.powerhouseweatherai.sumit.data.remote.dto.Weather

@Entity(tableName = "weather_details")
data class WeatherDetailEntity(
    @PrimaryKey val id: String= "0",
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "visibility") val visibility: Long?,
    @ColumnInfo(name = "coord") val coord: Coord?,
    @ColumnInfo(name = "dt") val dt: Long?,
    @ColumnInfo(name = "main") val main: Main?,
    @ColumnInfo(name = "weather") val weather: List<Weather>?
)

