package com.levirs.spacexlaunches.ui.utils

import android.content.Context
import com.levirs.spacexlaunches.R
import com.levirs.spacexlaunches.domain.util.DateTimePrecision
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime
import kotlin.math.ceil
import kotlin.math.floor

class LaunchDateTimeFormatter(
    val context: Context,
    val precision: DateTimePrecision
) {
    fun format(dt: OffsetDateTime): String {
        val text = arrayListOf<String>()
        val dateTime = ZonedDateTime.ofInstant(dt.toInstant(), ZoneOffset.systemDefault())
        if (precision.dayPrecise)
            text += dateTime.dayOfMonth.toString()
        if (precision.monthPrecise)
            text += context.resources
                .getStringArray(R.array.date_months)[dateTime.monthValue - 1]
        if (precision.quarterPrecise)
            text += context.getString(R.string.date_quarter,
                ceil(dateTime.monthValue / 3.0).toInt())
        if (precision.halfYearPrecise)
            text += context.getString(R.string.date_semester,
                ceil(dateTime.monthValue / 6.0).toInt())
        if (precision.yearPrecise)
            text += dateTime.year.toString()
        if (precision.hourPrecise)
            text += "${dateTime.hour}:${dateTime.minute}"
        return text.joinToString(separator = " ")
    }
}