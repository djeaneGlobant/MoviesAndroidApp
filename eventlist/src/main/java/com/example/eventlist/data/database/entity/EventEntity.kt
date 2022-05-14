package com.example.eventlist.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class EventEntity (
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
)