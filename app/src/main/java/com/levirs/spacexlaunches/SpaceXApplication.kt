package com.levirs.spacexlaunches

import android.app.Application
import android.content.Context
import com.levirs.spacexlaunches.di.AppComponent
import com.levirs.spacexlaunches.di.DaggerAppComponent
import com.levirs.spacexlaunches.di.DaggerCoreComponent

class SpaceXApplication: Application() {
    private val mCoreComponent by lazy {
        DaggerCoreComponent.factory().build(applicationContext)
    }
    val appComponent by lazy {
        DaggerAppComponent.factory().build(mCoreComponent)
    }
}

fun Context.getAppComponent(): AppComponent =
    (applicationContext as SpaceXApplication).appComponent