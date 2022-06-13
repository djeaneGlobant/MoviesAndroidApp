package com.example.localstorage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.localstorage.dao.BusinessDao
import com.example.localstorage.dao.EventDao
import com.example.localstorage.entity.BusinessEntity
import com.example.localstorage.entity.EventEntity

@Database(
    entities = [
        EventEntity::class,
        BusinessEntity::class
    ],
    version = 1
)
abstract class EventDataBase: RoomDatabase() {
    abstract fun eventDao(): EventDao

    abstract fun businessDao(): BusinessDao
}