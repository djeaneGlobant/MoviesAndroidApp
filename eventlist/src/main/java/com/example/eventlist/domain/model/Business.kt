package com.example.eventlist.domain.model

data class Business(
    val id: String,
    val name: String,
    val imageRestaurant: List<String>? = emptyList(),
    val rating: Double? = null,
    val phone: String? = null,
    val hours: List<Hours>? = emptyList(),
    val reviews: List<Review>? = emptyList(),
    var isFavorite: Boolean = false
) {


    data class Hours(
        val open: List<OpenHours>? = emptyList()
    )

    data class OpenHours(
        val day: Int,
        val start: String? = null,
        val end: String? = null
    )

    data class Review(
        val comment: String? = null,
        val rating: Int? = 0,
        val timeCreated: String? = null,
        val url: String? = null
    )
}

fun com.example.networkmodule.model.Business.toDomain() = Business(id, name, imageRestaurant, rating, phone, hours?.map { it.toDomain() }, reviews?.map { it.toDomain() })
private fun com.example.networkmodule.model.Hours.toDomain() = Business.Hours(open?.map { it.toDomain() })
private fun com.example.networkmodule.model.OpenHours.toDomain() = Business.OpenHours(day, start, end)
private fun com.example.networkmodule.model.Review.toDomain() = Business.Review(comment, rating, timeCreated, url)