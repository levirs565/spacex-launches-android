package com.levirs.spacexlaunches.core.data.local

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.levirs.spacexlaunches.core.data.local.entity.LocalLaunchEntity
import com.levirs.spacexlaunches.core.data.local.entity.LocalLaunchRocketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {
    @Transaction
    @RawQuery(observedEntities = [LocalLaunchEntity::class])
    fun getLaunchesWithRocket(rawQuery: SupportSQLiteQuery):
        PagingSource<Int, LocalLaunchRocketEntity>
    @Insert
    suspend fun saveLaunches(launch: LocalLaunchEntity)
    @Transaction
    @Query("SELECT * FROM launches WHERE is_favorite = 1")
    fun getFavoriteLaunches(): PagingSource<Int, LocalLaunchRocketEntity>
    @Transaction
    @Query("SELECT * FROM launches WHERE id = :id")
    fun getLaunchById(id: String): Flow<LocalLaunchRocketEntity>
    @Update
    suspend fun updateLaunches(launch: LocalLaunchEntity)
    @Query("SELECT id from launches")
    suspend fun getAllLaunchesIds(): List<String>
}
