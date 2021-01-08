package com.levirs.spacexlaunches.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.levirs.spacexlaunches.di.map.AppViewModelKey
import com.levirs.spacexlaunches.ui.launches.LaunchesViewModel
import com.levirs.spacexlaunches.ui.utils.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelModule {
    @Binds
    @IntoMap
    @AppViewModelKey(LaunchesViewModel::class)
    abstract fun bindLaunchesViewModel(launchesViewModel: LaunchesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory):
            ViewModelProvider.Factory
}