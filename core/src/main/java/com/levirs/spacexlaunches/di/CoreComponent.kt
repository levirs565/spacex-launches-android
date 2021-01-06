package com.levirs.spacexlaunches.di

import android.content.Context
import com.levirs.spacexlaunches.di.module.DatabaseModule
import com.levirs.spacexlaunches.di.module.NetworkModule
import com.levirs.spacexlaunches.di.module.RepositoryModule
import com.levirs.spacexlaunches.di.module.UseCaseModule
import com.levirs.spacexlaunches.domain.repository.LaunchesRepository
import com.levirs.spacexlaunches.domain.usecase.LaunchesUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, RepositoryModule::class, UseCaseModule::class])
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun build(@BindsInstance context: Context): CoreComponent
    }

    fun provideLaunchesUseCase(): LaunchesUseCase
}