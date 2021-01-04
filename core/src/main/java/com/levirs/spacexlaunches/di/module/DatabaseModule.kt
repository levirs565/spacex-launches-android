package com.levirs.spacexlaunches.di.module

import android.content.Context
import androidx.room.Room
import com.levirs.spacexlaunches.data.local.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideAppDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .build()

    @Provides
    fun provideLaunchDao(database: AppDatabase) = database.getLaunchDao()

    @Provides
    fun provideRocketDao(database: AppDatabase) = database.getRocketDao()
}