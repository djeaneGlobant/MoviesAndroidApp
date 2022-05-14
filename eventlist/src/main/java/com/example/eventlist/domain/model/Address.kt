package com.example.eventlist.domain.model

import com.example.eventlist.data.database.entity.AddressEntity
import com.example.eventlist.data.model.AddressModel

data class Address(
    val city: String,
    val country: String,
    val state: String
)

fun AddressModel.toDomain() = Address(city, country, state)

fun AddressEntity.toDomain() = Address(city, country, state)

fun Address.toEntity() = AddressEntity(city, country, state)