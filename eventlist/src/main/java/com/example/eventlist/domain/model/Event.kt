package com.example.eventlist.domain.model

class Event(
    val id: String,
    val imageUrl: String?,
    val name: String,
    val cost: Int,
    val isFree: Boolean,
    val timeStart: String,
    val timeEnd: String,
    val description: String,
    var isFavorite: Boolean = false,
    val location: Address,
    val business: Business?
)


internal fun com.example.networkmodule.model.Event.toDomain() = Event(
    id ?: "", imagePost, name ?: "", cost?: 0, isFree ?: false,timeStart ?: "", timeEnd?: "", description ?: "", false, location!!.toDomain(), business?.toDomain())