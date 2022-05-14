package com.example.eventlist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.eventlist.data.database.dao.EventDao
import com.example.eventlist.data.database.entity.EventEntity

@Database(
    entities = [
        EventEntity::class
    ],
    version = 1
)
abstract class EventDataBase: RoomDatabase() {
    abstract fun eventDao(): EventDao
}