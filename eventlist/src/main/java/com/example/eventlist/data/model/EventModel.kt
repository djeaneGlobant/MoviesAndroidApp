package com.example.eventlist.data.model

import com.example.eventlist.domain.model.Event
import com.google.gson.annotations.SerializedName

class EventModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("time_start")
    val timeStart: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("location")
    val location: AddressModel
)

class EventResponse(
    @SerializedName("events")
    val results: List<EventModel>
)