package com.levirs.spacexlaunches.domain.usecase

import androidx.paging.PagingData
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.domain.repository.LaunchesRepository
import com.levirs.spacexlaunches.domain.util.LaunchSortBy
import com.levirs.spacexlaunches.domain.util.ResultState
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

@Singleton
class LaunchInteractorImpl @Inject constructor(
    private val mRepository: LaunchesRepository
) : LaunchesUseCase {
    override fun getLaunches(
        scope: CoroutineScope,
        filterByName: String,
        filterByState: LaunchEntity.State?,
        sortBy: LaunchSortBy
    ): Flow<ResultState<PagingData<LaunchEntity>>> =
        mRepository.getLaunches(scope, filterByName, filterByState, sortBy)

    override fun getFavoriteLaunches(scope: CoroutineScope): Flow<PagingData<LaunchEntity>> =
        mRepository.getFavoriteLaunches(scope)

    override fun getLaunchById(id: String): Flow<LaunchEntity> =
        mRepository.getLaunchById(id)

    override suspend fun toggleFavoriteLaunch(launchEntity: LaunchEntity) {
        mRepository.updateLaunch(
            launchEntity.copy(isFavorite = !launchEntity.isFavorite)
        )
    }
}
