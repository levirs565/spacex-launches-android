package com.levirs.spacexlaunches.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.levirs.spacexlaunches.domain.entity.RocketEntity

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

    companion object {
        fun fromDomainRocket(rocket: RocketEntity) = LocalRocketEntity(
            id = rocket.id,
            name = rocket.name
        )
    }
}
