package com.levirs.spacexlaunches.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.levirs.spacexlaunches.core.data.local.entity.LocalRocketEntity

@Dao
interface RocketDao {
    @Insert
    suspend fun saveRocket(rocket: LocalRocketEntity)
    @Query("SELECT id from rockets")
    suspend fun getAllRocketIds(): List<String>
}
