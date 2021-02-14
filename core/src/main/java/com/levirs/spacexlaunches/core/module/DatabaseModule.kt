package com.levirs.spacexlaunches.core.module

import android.content.Context
import androidx.room.Room
import com.levirs.spacexlaunches.core.BuildConfig
import com.levirs.spacexlaunches.core.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Module
class DatabaseModule {
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        val passphrase = SQLiteDatabase.getBytes(BuildConfig.LIBRARY_PACKAGE_NAME.toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideLaunchDao(database: AppDatabase) = database.getLaunchDao()

    @Provides
    fun provideRocketDao(database: AppDatabase) = database.getRocketDao()
}
