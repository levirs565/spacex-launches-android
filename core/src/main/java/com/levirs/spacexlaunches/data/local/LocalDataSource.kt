package com.levirs.spacexlaunches.data.local

import androidx.paging.PagingSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.levirs.spacexlaunches.data.local.entity.LocalLaunchEntity
import com.levirs.spacexlaunches.data.local.entity.LocalLaunchRocketEntity
import com.levirs.spacexlaunches.data.local.entity.LocalRocketEntity
import com.levirs.spacexlaunches.domain.util.LaunchSortBy

class LocalDataSource(
    private val mLaunchDao: LaunchDao,
    private val mRocketDao: RocketDao
) {
    fun getLaunchesWithRocket(filterByName: String?, filterByState: LocalLaunchEntity.State?,
                              sortBy: LaunchSortBy): PagingSource<Int, LocalLaunchRocketEntity> {
        val where = arrayListOf<String>()
        if (!filterByName.isNullOrEmpty())
            where.add("name LIKE %$filterByName%")
        if (filterByState != null)
            where.add("state == ${filterByState.name}")

        var queryString = "SELECT * FROM launches"
        if (where.isNotEmpty()) queryString += where.joinToString(", ", prefix = " ")
        queryString += " ORDER BY " + when (sortBy) {
            LaunchSortBy.FLIGHT_NUMBER_ASC -> "flight_number"
            LaunchSortBy.FLIGHT_NUMBER_DESC -> "flight_number DESC"
            LaunchSortBy.NAME_ASC -> "name"
            LaunchSortBy.NAME_DESC -> "name DESC"
        }

        return mLaunchDao.getLaunchesWithRocket(SimpleSQLiteQuery(queryString))
    }
    fun getFavoriteLaunches() = mLaunchDao.getFavoriteLaunches()
    fun getAllLaunchesIds() = mLaunchDao.getAllLaunchesIds()
    fun saveLaunch(launch: LocalLaunchEntity) = mLaunchDao.saveLaunches(launch)
    fun updateLaunch(launch: LocalLaunchEntity) = mLaunchDao.updateLaunches(launch)

    fun getAllRocketsIds() = mRocketDao.getAllRocketIds()
    fun saveRocket(rocket: LocalRocketEntity) = mRocketDao.saveRocket(rocket)
}