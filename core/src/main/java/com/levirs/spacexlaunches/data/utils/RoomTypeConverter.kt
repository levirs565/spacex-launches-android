package com.levirs.spacexlaunches.data.utils

import androidx.room.TypeConverter
import com.levirs.spacexlaunches.data.local.entity.LocalLaunchEntity
import com.levirs.spacexlaunches.domain.util.DateTimePrecision
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter

object RoomTypeConverter {
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    @JvmStatic
    fun offsetDateTimeToString(offsetDateTime: OffsetDateTime?): String? {
        return offsetDateTime?.format(formatter)
    }

    @TypeConverter
    @JvmStatic
    fun stringToOffsetDateTime(string: String?): OffsetDateTime? {
        return string?.let {
            OffsetDateTime.parse(it, formatter)
        }
    }

    @TypeConverter
    @JvmStatic
    fun stateToString(state: LocalLaunchEntity.State?): String? {
        return state?.toString()
    }

    @TypeConverter
    @JvmStatic
    fun stringToState(string: String?): LocalLaunchEntity.State? {
        return string?.let {
            LocalLaunchEntity.State.valueOf(it)
        }
    }

    @TypeConverter
    @JvmStatic
    fun dateTimePrecisionToString(dateTimePrecision: DateTimePrecision?): String? {
        return dateTimePrecision?.name
    }

    @TypeConverter
    @JvmStatic
    fun stringToDateTimePrecision(string: String?): DateTimePrecision? {
        return string?.let {
            DateTimePrecision.valueOf(it)
        }
    }
}