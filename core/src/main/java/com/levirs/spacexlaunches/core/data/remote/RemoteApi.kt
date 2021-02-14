package com.levirs.spacexlaunches.core.data.remote

import com.levirs.spacexlaunches.core.data.remote.entity.RemoteLaunchEntity
import com.levirs.spacexlaunches.core.data.remote.entity.RemoteRocketEntity
import retrofit2.http.GET

interface RemoteApi {
    @GET("launches")
    suspend fun getLaunches(): List<RemoteLaunchEntity>

    @GET("rockets")
    suspend fun getRockets(): List<RemoteRocketEntity>
}
