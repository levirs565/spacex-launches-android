package com.levirs.spacexlaunches.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.levirs.spacexlaunches.data.local.LocalDataSource
import com.levirs.spacexlaunches.data.local.entity.LocalLaunchEntity
import com.levirs.spacexlaunches.data.remote.RemoteDataSource
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.domain.repository.LaunchesRepository
import com.levirs.spacexlaunches.domain.util.LaunchSortBy
import com.levirs.spacexlaunches.domain.util.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class LaunchesDataSource(
    private val mRemoteDataSource: RemoteDataSource,
    private val mLocalDataSource: LocalDataSource
): LaunchesRepository {
    private val mPageConfig = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false,
        initialLoadSize = 20
    )
    private var mRocketsChecked = false
    private var mLaunchesChecked = false

    private fun checkRocket() {
        if (mRocketsChecked) return

        if (mLocalDataSource.getAllRocketsIds().isEmpty())
            mRemoteDataSource.getRockets().forEach {
                mLocalDataSource.saveRocket(it.toLocalRocket())
            }
        mRocketsChecked = true
    }

    private fun checkLaunches() {
        if (mLaunchesChecked) return

        if (mLocalDataSource.getAllLaunchesIds().isEmpty())
            mRemoteDataSource.getLaunches().forEach {
                mLocalDataSource.saveLaunch(it.toLocalLaunch())
            }
        mLaunchesChecked = true
    }

    override fun getLaunches(
        filterByName: String,
        filterByState: LaunchEntity.State?,
        sortBy: LaunchSortBy
    ): Flow<ResultState<PagingData<LaunchEntity>>> = flow {
        emit(ResultState.loading<PagingData<LaunchEntity>>())
        try {
            checkRocket()
            checkLaunches()
        } catch (e: Exception) {
            emit(ResultState.error<PagingData<LaunchEntity>>(e.toString()))
            return@flow
        }

        val dbSource = Pager(mPageConfig) {
            mLocalDataSource.getLaunchesWithRocket(
                filterByName, filterByState?.let {
                    LocalLaunchEntity.State.fromDomainLaunchState(it)
                                                 }, sortBy)
        }.flow
        emitAll(dbSource.map {
            ResultState.succes(it.map { item ->
                item.toDomainLaunch()
            })
        })
    }

    override fun getFavoriteLaunches(): Flow<PagingData<LaunchEntity>>
        = Pager(mPageConfig) {
            mLocalDataSource.getFavoriteLaunches()
    }.flow.map {
        it.map { localRocket ->
            localRocket.toDomainLaunch()
        }
    }

    override fun updateLaunch(launch: LaunchEntity) {
        mLocalDataSource.updateLaunch(LocalLaunchEntity.fromDomainLaunch(launch))
    }
}