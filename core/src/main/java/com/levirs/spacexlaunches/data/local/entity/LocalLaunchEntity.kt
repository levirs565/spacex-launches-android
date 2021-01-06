package com.levirs.spacexlaunches.data.local.entity

import androidx.room.*
import com.levirs.spacexlaunches.domain.entity.LaunchEntity
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "launches")
data class LocalLaunchEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    @ColumnInfo(name = "flight_number")
    val flightNumber: Int,
    val details: String?,
    @ColumnInfo(name = "rocket_id")
    val rocketId: String,
    val state: State,
    @ColumnInfo(name = "small_patch")
    val smallPatch: String?,
    @ColumnInfo(name = "large_patch")
    val largePatch: String?,
    @Embedded
    val links: Links?,
    @ColumnInfo(name = "launch_date_time")
    val launchDateTime: OffsetDateTime,
    @ColumnInfo(name = "date_precision")
    val datePrecision: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean?
) {

    data class Links(
        val webcast: String?,
        val wikipedia: String?,
        val article: String?,
    ) {
        fun toDomainLaunchLinks() = LaunchEntity.Links(
            webcast = this.webcast,
            wikipedia = this.wikipedia,
            article = this.article
        )
        companion object {
            fun fromDomainLaunchLinks(links: LaunchEntity.Links) = Links(
                webcast = links.webcast,
                wikipedia = links.wikipedia,
                article = links.article
            )
        }
    }

    enum class State {
        UPCOMING,
        SUCCESS,
        FAIL;

        fun toDomainLaunchState() = LaunchEntity.State.valueOf(toString())
        companion object {
            fun fromDomainLaunchState(state: LaunchEntity.State?) =  valueOf(state.toString())
        }
    }

    companion object {
        fun fromDomainLaunch(launch: LaunchEntity) = LocalLaunchEntity(
            id = launch.id,
            name = launch.name,
            flightNumber = launch.flightNumber,
            details = launch.details,
            rocketId = launch.rocket.id,
            state = State.fromDomainLaunchState(launch.state),
            smallPatch = launch.smallPatch,
            largePatch = launch.largePatch,
            links = launch.links?.let {
                Links.fromDomainLaunchLinks(it)
            },
            launchDateTime = launch.launchDateTime,
            datePrecision = launch.datePrecision,
            isFavorite = launch.isFavorite
        )
    }
}
