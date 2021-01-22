package com.levirs.spacexlaunches.di.module

import com.levirs.spacexlaunches.domain.usecase.LaunchInteractorImpl
import com.levirs.spacexlaunches.domain.usecase.LaunchesUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {
    @Binds
    abstract fun bindLaunchesUseCase(interactor: LaunchInteractorImpl): LaunchesUseCase
}
