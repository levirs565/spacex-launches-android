package com.levirs.spacexlaunches.core.module

import com.levirs.spacexlaunches.core.domain.usecase.LaunchInteractorImpl
import com.levirs.spacexlaunches.core.domain.usecase.LaunchesUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {
    @Binds
    abstract fun bindLaunchesUseCase(interactor: LaunchInteractorImpl): LaunchesUseCase
}
