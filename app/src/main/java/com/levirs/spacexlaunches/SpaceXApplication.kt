package com.levirs.spacexlaunches

import android.app.Application
import android.content.Context
import com.levirs.spacexlaunches.core.CoreComponent
import com.levirs.spacexlaunches.core.DaggerCoreComponent
import com.levirs.spacexlaunches.di.AppComponent
import com.levirs.spacexlaunches.di.DaggerAppComponent

class SpaceXApplication : Application() {
    val coreComponent by lazy {
        DaggerCoreComponent.factory().build(applicationContext)
    }
    val appComponent by lazy {
        DaggerAppComponent.factory().build(coreComponent)
    }
}

fun Context.getAppComponent(): AppComponent =
    (applicationContext as SpaceXApplication).appComponent
fun Context.getCoreComponent(): CoreComponent =
    (applicationContext as SpaceXApplication).coreComponent
