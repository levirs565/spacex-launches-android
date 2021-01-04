package com.levirs.spacexlaunches.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val mApi: RemoteApi
) {
    suspend fun getLaunches() = mApi.getLaunches()
    suspend fun getRockets() = mApi.getRockets()
}