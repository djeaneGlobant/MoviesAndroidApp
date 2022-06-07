package com.example.eventlist.domain.model

import com.example.networkmodule.model.Hours
import com.example.networkmodule.model.Review

data class Business(
    val id: String,
    val name: String,
    val imageRestaurant: List<String>? = emptyList(),
    val rating: Double?,
    val phone: String?,
    val hours: List<Hours>? = emptyList(),
    val reviews: List<Review>? = emptyList()
)

internal fun com.example.networkmodule.model.Business.toDomain() = Business(id, name, imageRestaurant, rating, phone, hours, reviews)
