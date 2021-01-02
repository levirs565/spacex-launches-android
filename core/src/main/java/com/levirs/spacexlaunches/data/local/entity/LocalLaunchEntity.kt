package com.levirs.spacexlaunches.data.local.entity

import androidx.room.*
import org.threeten.bp.OffsetDateTime

@Entity(tableName = "launches")
data class LocalLaunchEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    @ColumnInfo(name = "flight_number")
    val flightNumber: Int,
    val details: String,
    @ColumnInfo(name = "rocket_id")
    val rocketId: String,
    val state: State,
    @ColumnInfo(name = "small_patch")
    val smallPatch: String,
    @ColumnInfo(name = "large_patch")
    val largePatch: String,
    @Embedded
    val links: Links,
    @ColumnInfo(name = "launch_date_time")
    val launchDateTime: OffsetDateTime,
    @ColumnInfo(name = "date_precision")
    val datePrecision: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean?
) {

    data class Links(
        val webcast: String,
        val wikipedia: String,
        val article: String,
    )

    enum class State {
        UPCOMING,
        SUCCESS,
        FAIL
    }
}
