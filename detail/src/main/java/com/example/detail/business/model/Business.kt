package com.example.detail.business.model

internal data class Business(
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
        val url: String? = null,
        val user: User? = null
    )

    data class User(
        val profileUrl: String,
        val imageUrl: String?,
        val name: String
    )
}