package com.levirs.spacexlaunches.domain.util

enum class DateTimePrecision(
    val hourPrecise: Boolean = false,
    val dayPrecise: Boolean = false,
    val monthPrecise: Boolean = false,
    val quarterPrecise: Boolean = false,
    val halfYearPrecise: Boolean = false,
    val yearPrecise: Boolean = false,
) {
    QUARTER(yearPrecise = true, quarterPrecise = true),
    HALF(yearPrecise = true, halfYearPrecise = true),
    YEAR(yearPrecise = true),
    MONTH(monthPrecise = true, yearPrecise = true),
    DAY(dayPrecise = true, monthPrecise = true, yearPrecise = true),
    HOUR(hourPrecise = true, dayPrecise = true, monthPrecise = true, yearPrecise = true);
}