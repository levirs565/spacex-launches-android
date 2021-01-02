package com.levirs.spacexlaunches.domain.repository

import androidx.paging.PagingData
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.domain.util.LaunchSortBy
import com.levirs.spacexlaunches.domain.util.ResultState
import kotlinx.coroutines.flow.Flow

interface LaunchesRepository {

    fun getLaunches(search: String, filterByState: LaunchEntity.State?, sortBy: LaunchSortBy)
        : Flow<ResultState<PagingData<LaunchEntity>>>
    fun getFavoriteLaunches(): Flow<PagingData<LaunchEntity>>
    fun updateLaunch(launch: LaunchEntity)
}