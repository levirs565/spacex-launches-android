package com.levirs.spacexlaunches.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "rockets")
data class LocalRocketEntity(
    @PrimaryKey
    val id: String,
    val name: String
)