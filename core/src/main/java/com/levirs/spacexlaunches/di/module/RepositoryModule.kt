package com.levirs.spacexlaunches.di.module

import com.levirs.spacexlaunches.data.LaunchesDataSource
import com.levirs.spacexlaunches.domain.repository.LaunchesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindLaunchesRepository(datasource: LaunchesDataSource): LaunchesRepository
}
