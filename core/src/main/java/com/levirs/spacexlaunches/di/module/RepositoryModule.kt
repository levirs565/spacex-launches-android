package com.levirs.spacexlaunches.di.module

import com.levirs.spacexlaunches.data.LaunchesDataSource
import com.levirs.spacexlaunches.domain.repository.LaunchesRepository
import com.levirs.spacexlaunches.domain.usecase.LaunchInteractorImpl
import com.levirs.spacexlaunches.domain.usecase.LaunchesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindLaunchesRepository(datasource: LaunchesDataSource): LaunchesRepository
}