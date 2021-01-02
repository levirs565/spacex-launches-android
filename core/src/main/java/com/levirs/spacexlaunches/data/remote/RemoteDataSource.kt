package com.levirs.spacexlaunches.data.remote

import com.levirs.spacexlaunches.data.remote.entity.RemoteLaunchEntity
import com.levirs.spacexlaunches.data.remote.entity.RemoteRocketEntity
import retrofit2.http.GET

interface RemoteDataSource {
    @GET("launches")
    fun getLaunches(): List<RemoteLaunchEntity>

    @GET("rockets")
    fun getRockets(): List<RemoteRocketEntity>
}