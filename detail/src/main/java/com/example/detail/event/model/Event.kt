package com.example.detail.event.model

data class Event(
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
) {
    data class Address(
        val city: String
    )

    data class Business(
        val id: String,
        val name: String,
        val imageRestaurant: List<String>
    )
}