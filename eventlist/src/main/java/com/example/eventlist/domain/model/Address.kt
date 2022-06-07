package com.example.eventlist.domain.model

data class Address(
    val city: String,
    val country: String,
    val state: String
)

internal fun com.example.networkmodule.model.Location.toDomain() = Address(city!!, state!!, country!!)