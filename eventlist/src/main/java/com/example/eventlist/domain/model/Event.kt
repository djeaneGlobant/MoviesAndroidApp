package com.example.eventlist.domain.model

class Event(
    val id: String,
    val imageUrl: String?,
    val name: String,
    val timeStart: String,
    val description: String,
    var isFavorite: Boolean = false,
    val location: Address
)


internal fun com.example.networkmodule.model.Event.toDomain() = Event(id ?: "", imagePost, name ?: "", timeStart ?: "", description ?: "", false, location!!.toDomain())