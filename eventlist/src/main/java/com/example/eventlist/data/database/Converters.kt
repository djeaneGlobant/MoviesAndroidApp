package com.example.eventlist.data.database

import androidx.room.TypeConverter
import com.example.eventlist.data.database.entity.AddressEntity
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun toEntity(addressString: String): AddressEntity {
        return Gson().fromJson(addressString, AddressEntity::class.java)
    }

    @TypeConverter
    fun toString(addressEntity: AddressEntity): String? {
        return Gson().toJson(addressEntity)
    }
}