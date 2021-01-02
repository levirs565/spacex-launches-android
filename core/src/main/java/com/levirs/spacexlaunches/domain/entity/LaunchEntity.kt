package com.levirs.spacexlaunches.domain.entity

import org.threeten.bp.OffsetDateTime

data class LaunchEntity(
    val id: String,
    val name: String,
    val flightNumber: Int,
    val details: String,
    val rocketName: String,
    val state: State,
    val smallPatch: String,
    val largePatch: String,
    val links: Map<String, String>,
    val launchDateTime: OffsetDateTime,
    val datePrecision: String,
    val isFavorite: Boolean
) {
    enum class State {
        UPCOMING,
        SUCCESS,
        FAIL
    }
}