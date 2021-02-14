package com.levirs.spacexlaunches.core.module

import com.levirs.spacexlaunches.core.data.LaunchesDataSource
import com.levirs.spacexlaunches.core.domain.repository.LaunchesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindLaunchesRepository(datasource: LaunchesDataSource): LaunchesRepository
}
