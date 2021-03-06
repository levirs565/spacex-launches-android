package com.levirs.spacexlaunches.di

import com.levirs.spacexlaunches.core.CoreComponent
import com.levirs.spacexlaunches.di.module.AppViewModelModule
import com.levirs.spacexlaunches.feature.FeatureScope
import com.levirs.spacexlaunches.ui.detail.DetailActivity
import com.levirs.spacexlaunches.ui.launches.LaunchesFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun build(coreComponent: CoreComponent): AppComponent
    }

    fun injectLaunchesFragment(fragment: LaunchesFragment)
    fun injectDetailActivity(activity: DetailActivity)
}
