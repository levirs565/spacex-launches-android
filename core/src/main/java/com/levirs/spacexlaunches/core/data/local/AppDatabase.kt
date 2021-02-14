package com.levirs.spacexlaunches.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.levirs.spacexlaunches.core.data.local.entity.LocalLaunchEntity
import com.levirs.spacexlaunches.core.data.local.entity.LocalRocketEntity
import com.levirs.spacexlaunches.core.data.utils.RoomTypeConverter

@Database(
    entities = [LocalLaunchEntity::class, LocalRocketEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getLaunchDao(): LaunchDao
    abstract fun getRocketDao(): RocketDao
}
