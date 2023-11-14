package com.powerhouseweatherai.sumit.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.powerhouseweatherai.sumit.data.remote.dto.Coord
import com.powerhouseweatherai.sumit.data.remote.dto.Main
import com.powerhouseweatherai.sumit.data.remote.dto.Weather

class Converters {
    @TypeConverter
    fun fromWeatherList(value: List<Weather>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toWeatherList(value: String?): List<Weather>? {
        val listType = object : TypeToken<List<Weather>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromMain(value: Main?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toMain(value: String?): Main? {
        return Gson().fromJson(value, Main::class.java)
    }

    @TypeConverter
    fun fromCoord(value: Coord?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCoord(value: String?): Coord? {
        return Gson().fromJson(value, Coord::class.java)
    }
}