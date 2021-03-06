package com.levirs.spacexlaunches.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.levirs.spacexlaunches.core.domain.entity.RocketEntity

@Entity(tableName = "rockets")
data class LocalRocketEntity(
    @PrimaryKey
    val id: String,
    val name: String
) {
    fun toDomainRocket() = RocketEntity(
        id = this.id,
        name = this.name
    )
}
