package com.example.eventlist.domain.model

data class Address(
    val city: String
)

internal fun com.example.networkmodule.model.Location.toDomain() = Address(city ?: "")