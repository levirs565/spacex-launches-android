package com.levirs.spacexlaunches.domain.entity

import org.threeten.bp.OffsetDateTime

data class LaunchEntity(
    val id: String,
    val name: String,
    val flightNumber: Int,
    val details: String,
    val rocket: RocketEntity,
    val state: State,
    val smallPatch: String,
    val largePatch: String,
    val links: Links,
    val launchDateTime: OffsetDateTime,
    val datePrecision: String,
    val isFavorite: Boolean
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