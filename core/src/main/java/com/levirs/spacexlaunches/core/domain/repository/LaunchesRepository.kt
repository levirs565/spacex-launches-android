package com.levirs.spacexlaunches.core.domain.repository

import androidx.paging.PagingData
import com.levirs.spacexlaunches.core.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.core.domain.util.LaunchSortBy
import com.levirs.spacexlaunches.core.domain.util.ResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {

    fun getLaunches(
        scope: CoroutineScope,
        filterByName: String,
        filterByState: LaunchEntity.State?,
        sortBy: LaunchSortBy
    ):
        Flow<ResultState<PagingData<LaunchEntity>>>
    fun getFavoriteLaunches(scope: CoroutineScope): Flow<PagingData<LaunchEntity>>
    fun getLaunchById(id: String): Flow<LaunchEntity>
    suspend fun updateLaunch(launch: LaunchEntity)
}
