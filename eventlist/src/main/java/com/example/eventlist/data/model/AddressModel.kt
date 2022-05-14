package com.example.eventlist.data.model

import com.google.gson.annotations.SerializedName

data class AddressModel(
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("state")
    val state: String
)