package com.powerhouseweatherai.sumit.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profileData: WeatherDetailsEntity)

    @Query("SELECT * FROM profile_data")
    fun getProfileData(): WeatherDetailsEntity?
}