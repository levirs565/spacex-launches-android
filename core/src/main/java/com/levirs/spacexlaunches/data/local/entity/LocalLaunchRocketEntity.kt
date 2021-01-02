package com.levirs.spacexlaunches.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class LocalLaunchRocketEntity(
    @Embedded
    val launch: LocalLaunchEntity,
    @Relation(
        parentColumn = "rocket_id",
        entityColumn = "id"
    )
    val rocket: LocalRocketEntity
)