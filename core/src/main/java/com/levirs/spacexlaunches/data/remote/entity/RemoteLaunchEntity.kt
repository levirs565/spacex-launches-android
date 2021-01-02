package com.levirs.spacexlaunches.data.remote.entity

import com.google.gson.annotations.SerializedName
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

        data class Patch(
            val small: String,
            val large: String
        )

    }

}
