package com.example.localstorage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.localstorage.dao.EventDao
import com.example.localstorage.entity.EventEntity

@Database(
    entities = [
        EventEntity::class
    ],
    version = 1
)
abstract class EventDataBase: RoomDatabase() {
    abstract fun eventDao(): EventDao
}