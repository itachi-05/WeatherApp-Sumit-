package com.powerhouseweatherai.sumit.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [WeatherDetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDetailsDatabase : RoomDatabase() {
    abstract fun weatherDetailsDao(): WeatherDetailsDao
}