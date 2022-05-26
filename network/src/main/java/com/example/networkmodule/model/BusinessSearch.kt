package com.example.networkmodule.model

import com.google.gson.annotations.SerializedName

data class BusinessData(
    val search: BusinessSearch
)

data class BusinessSearch(
    val total: Int? = 0,
    val business: List<Business>? = emptyList()
)

data class Business(
    val id: String,
    val name: String,
    @SerializedName("photos") val imageRestaurant: List<String>? = emptyList(),
    val rating: Double? = null,
    val phone: String? = null,
    val hours: List<Hours>? = emptyList(),
    val reviews: List<Review>? = emptyList()
)

data class Hours(
    val open: List<OpenHours>? = emptyList()
)

data class OpenHours(
    val day: Int,
    val start: String? = null,
    val end: String? = null
)
data class Review(
    @SerializedName("text") val comment: String? = null,
    val rating: Int? = 0,
    @SerializedName("time_created") val timeCreated: String? = null,
    val url: String? = null
)