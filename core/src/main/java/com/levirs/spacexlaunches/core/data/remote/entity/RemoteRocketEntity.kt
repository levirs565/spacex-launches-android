package com.levirs.spacexlaunches.core.data.remote.entity

import com.levirs.spacexlaunches.core.data.local.entity.LocalRocketEntity

data class RemoteRocketEntity(
    val id: String,
    val name: String
) {
    fun toLocalRocket() = LocalRocketEntity(
        id = this.id,
        name = this.name
    )
}
