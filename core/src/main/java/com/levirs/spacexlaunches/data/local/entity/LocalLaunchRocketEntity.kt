package com.levirs.spacexlaunches.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.levirs.spacexlaunches.domain.entity.LaunchEntity

data class LocalLaunchRocketEntity(
    @Embedded
    val launch: LocalLaunchEntity,
    @Relation(
        parentColumn = "rocket_id",
        entityColumn = "id"
    )
    val rocket: LocalRocketEntity
) {
    fun toDomainLaunch() = LaunchEntity(
        id = launch.id,
        name = launch.name,
        flightNumber = launch.flightNumber,
        details = launch.details,
        rocket = rocket.toDomainRocket(),
        state = launch.state.toDomainLaunchState(),
        smallPatch = launch.smallPatch,
        largePatch = launch.largePatch,
        links = launch.links?.toDomainLaunchLinks(),
        launchDateTime = launch.launchDateTime,
        datePrecision = launch.datePrecision,
        isFavorite = launch.isFavorite ?: false,
    )
}
