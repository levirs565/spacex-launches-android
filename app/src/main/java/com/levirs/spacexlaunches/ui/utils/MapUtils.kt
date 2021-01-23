package com.levirs.spacexlaunches.ui.utils

fun <K, V> Map<K, V>.getKey(value: V): K =
    entries.first {
        it.value == value
    }.key