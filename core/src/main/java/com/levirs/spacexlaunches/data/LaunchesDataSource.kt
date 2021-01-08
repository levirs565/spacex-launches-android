package com.levirs.spacexlaunches.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.levirs.spacexlaunches.data.local.LocalDataSource
import com.levirs.spacexlaunches.data.local.entity.LocalLaunchEntity
import com.levirs.spacexlaunches.data.remote.RemoteApi
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import com.levirs.spacexlaunches.domain.repository.LaunchesRepository
import com.levirs.spacexlaunches.domain.util.LaunchSortBy
import com.levirs.spacexlaunches.domain.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchesDataSource @Inject constructor(
    private val mRemoteApi: RemoteApi,
    private val mLocalDataSource: LocalDataSource
): LaunchesRepository {
    private val mPageConfig = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false,
        initialLoadSize = 20
    )
    private var mRocketsChecked = false
    private var mLaunchesChecked = false

    private suspend fun checkRocket() {
        if (mRocketsChecked) return

        if (mLocalDataSource.getAllRocketsIds().isEmpty())
            mRemoteApi.getRockets().forEach {
                mLocalDataSource.saveRocket(it.toLocalRocket())
            }
        mRocketsChecked = true
    }

    private suspend fun checkLaunches() {
        if (mLaunchesChecked) return

        if (mLocalDataSource.getAllLaunchesIds().isEmpty())
            mRemoteApi.getLaunches().forEach {
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
            e.printStackTrace()
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
    }.flowOn(Dispatchers.IO)

    override fun getFavoriteLaunches(): Flow<PagingData<LaunchEntity>>
        = Pager(mPageConfig) {
            mLocalDataSource.getFavoriteLaunches()
    }.flow.map {
        it.map { localRocket ->
            localRocket.toDomainLaunch()
        }
    }

    override fun getLaunchById(id: String): Flow<LaunchEntity>
        = mLocalDataSource.getLaunchById(id).map {
            it.toDomainLaunch()
    }

    override suspend fun updateLaunch(launch: LaunchEntity) {
        mLocalDataSource.updateLaunch(LocalLaunchEntity.fromDomainLaunch(launch))
    }
}