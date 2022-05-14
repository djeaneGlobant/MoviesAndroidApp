package com.example.eventlist.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class EventEntity (
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "time_start")
    val timeStart: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean,
    @ColumnInfo(name = "location")
    val location: AddressEntity
)