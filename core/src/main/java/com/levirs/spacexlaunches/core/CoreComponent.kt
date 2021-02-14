package com.levirs.spacexlaunches.core

import android.content.Context
import com.levirs.spacexlaunches.core.domain.usecase.LaunchesUseCase
import com.levirs.spacexlaunches.core.module.DatabaseModule
import com.levirs.spacexlaunches.core.module.NetworkModule
import com.levirs.spacexlaunches.core.module.RepositoryModule
import com.levirs.spacexlaunches.core.module.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface CoreComponent {
    @Component.Factory
    interface Factory {
        fun build(@BindsInstance context: Context): CoreComponent
    }

    fun provideLaunchesUseCase(): LaunchesUseCase
}
