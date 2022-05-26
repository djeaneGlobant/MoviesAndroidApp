package com.example.networkmodule.model

import com.google.gson.annotations.SerializedName

data class EventData (
    @SerializedName("event_search") val eventSearch: EventSearch
)

data class EventSearch(
    val total: Int,
    val events: List<Event>? = emptyList()
)

data class Event(
    val id: String,
    val location: Location? = null,
    val name: String? = null,
    val cost: Int? = 0,
    @SerializedName("image_url") val imagePost: String? = null,
    val description: String? = null,
    @SerializedName("time_start") val timeStart: String? = null,
    @SerializedName("time_end") val timeEnd: String? = null,
    val business: Business? = null
)

data class Location(
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    @SerializedName("formatted_address") val address: String? = null
)