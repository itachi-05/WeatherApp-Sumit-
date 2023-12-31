package com.powerhouseweatherai.sumit.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherDetail: WeatherDetailEntity)

    @Query("SELECT * FROM weather_details WHERE id = :id")
    suspend fun getWeatherDetail(id:String): WeatherDetailEntity?


    @Query("DELETE FROM weather_details")
    suspend fun clearWeatherDetails()
}
