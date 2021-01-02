package com.levirs.spacexlaunches.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.levirs.spacexlaunches.data.local.entity.LocalRocketEntity

@Dao
interface RocketDao {
    @Insert
    fun saveRocket(rocket: LocalRocketEntity)
    @Query("SELECT id from rockets")
    fun getAllRocketIds(): List<String>
}