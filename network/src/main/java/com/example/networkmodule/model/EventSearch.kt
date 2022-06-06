package com.example.networkmodule.model

import com.google.gson.annotations.SerializedName

data class EventData (
    @SerializedName("event_search") val eventSearch: EventSearch
)

data class EventSearch(
    @SerializedName("total") val total: Int,
    @SerializedName("events") val events: List<Event>? = emptyList()
)

data class Event(
    @SerializedName("id") val id: String,
    @SerializedName("location") val location: Location? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("cost") val cost: Int? = 0,
    @SerializedName("image_url") val imagePost: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("time_start") val timeStart: String? = null,
    @SerializedName("time_end") val timeEnd: String? = null,
    @SerializedName("business") val business: Business? = null
)

data class Location(
    @SerializedName("city") val city: String? = null,
    @SerializedName("state") val state: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("formatted_address") val address: String? = null
)