package com.levirs.spacexlaunches.data.remote

class RemoteDataSource(
    private val mApi: RemoteApi
) {
    suspend fun getLaunches() = mApi.getLaunches()
    suspend fun getRockets() = mApi.getRockets()
}