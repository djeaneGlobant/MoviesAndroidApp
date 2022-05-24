package com.example.eventlist.domain.model

import com.example.eventlist.data.database.entity.EventEntity
import com.example.eventlist.data.model.EventModel

class Event(
    val id: String,
    val imageUrl: String?,
    val name: String,
    val timeStart: String,
    val description: String,
    var isFavorite: Boolean = false,
    val location: Address
)

fun EventModel.toDomain() = Event(id, imageUrl, name, timeStart, description, location = location.toDomain())

fun EventEntity.toDomain() = Event(id, imageUrl, name, timeStart, description, isFavorite, location.toDomain())

fun Event.toEntity() = EventEntity(id, imageUrl, name, timeStart, description, isFavorite, location.toEntity())