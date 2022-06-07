package com.example.networkmodule.model

import com.google.gson.annotations.SerializedName

data class BusinessData(
    @SerializedName("search") val search: BusinessSearch
)

data class BusinessSearch(
    @SerializedName("total") val total: Int? = 0,
    @SerializedName("business") val business: List<Business>? = emptyList()
)

data class Business(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("photos") val imageRestaurant: List<String>? = emptyList(),
    @SerializedName("rating") val rating: Double? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("hours") val hours: List<Hours>? = emptyList(),
    @SerializedName("reviews") val reviews: List<Review>? = emptyList()
)

data class Hours(
    @SerializedName("open") val open: List<OpenHours>? = emptyList()
)

data class OpenHours(
    @SerializedName("day") val day: Int,
    @SerializedName("start") val start: String? = null,
    @SerializedName("end") val end: String? = null
)
data class Review(
    @SerializedName("text") val comment: String? = null,
    @SerializedName("rating") val rating: Int? = 0,
    @SerializedName("time_created") val timeCreated: String? = null,
    @SerializedName("url") val url: String? = null
)