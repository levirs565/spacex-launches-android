package com.levirs.spacexlaunches.data.remote.entity

import com.levirs.spacexlaunches.data.local.entity.LocalRocketEntity

data class RemoteRocketEntity(
    val id: String,
    val name: String
) {
    fun toLocalRocket() = LocalRocketEntity(
        id = this.id,
        name = this.name
    )
}