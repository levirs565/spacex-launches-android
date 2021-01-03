package com.levirs.spacexlaunches.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.levirs.spacexlaunches.data.local.entity.LocalLaunchEntity
import org.threeten.bp.OffsetDateTime

data class RemoteLaunchEntity(
    val id: String,
    val name: String,
    @SerializedName("flight_number")
    val flightNumber: Int,
    val details: String,
    @SerializedName("rocket")
    val rocketId: String,
    val success: Boolean,
    val upcoming: Boolean,
    val links: Links,
    @SerializedName("date_precision")
    val datePrecision: String,
    @SerializedName("date_local")
    val dateTime: OffsetDateTime
) {

    data class Links(
        val patch: Patch,
        val webcast: String,
        val wikipedia: String,
        val article: String,
    ) {
        fun toLocalLaunchLinks() = LocalLaunchEntity.Links(
            webcast = this.webcast,
            wikipedia = this.wikipedia,
            article = this.article
        )

        data class Patch(
            val small: String,
            val large: String
        )

    }

    fun toLocalLaunch() = LocalLaunchEntity(
        id = this.id,
        name = this.name,
        flightNumber = this.flightNumber,
        details = this.details,
        rocketId = this.rocketId,
        state = when {
            upcoming -> LocalLaunchEntity.State.UPCOMING
            success -> LocalLaunchEntity.State.SUCCESS
            else -> LocalLaunchEntity.State.FAIL
        },
        smallPatch = this.links.patch.small,
        largePatch = this.links.patch.large,
        links = this.links.toLocalLaunchLinks(),
        launchDateTime = this.dateTime,
        datePrecision = this.datePrecision,
        isFavorite = null
    )
}
