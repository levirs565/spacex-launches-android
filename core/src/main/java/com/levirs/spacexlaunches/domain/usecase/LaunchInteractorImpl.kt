package com.levirs.spacexlaunches.domain.usecase

import androidx.paging.PagingData
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.domain.repository.LaunchesRepository
import com.levirs.spacexlaunches.domain.util.LaunchSortBy
import com.levirs.spacexlaunches.domain.util.ResultState
import kotlinx.coroutines.flow.Flow

class LaunchInteractorImpl(
    private val mRepository: LaunchesRepository
): LaunchesUseCase {
    override fun getLaunches(
        filterByName: String,
        filterByState: LaunchEntity.State?,
        sortBy: LaunchSortBy
    ): Flow<ResultState<PagingData<LaunchEntity>>>
        = mRepository.getLaunches(filterByName, filterByState, sortBy)


    override fun getFavoriteLaunches(): Flow<PagingData<LaunchEntity>>
        = mRepository.getFavoriteLaunches()

    override fun toggleFavoriteLaunch(launchEntity: LaunchEntity) {
        mRepository.updateLaunch(
            launchEntity.copy(isFavorite = !launchEntity.isFavorite)
        )
    }
}