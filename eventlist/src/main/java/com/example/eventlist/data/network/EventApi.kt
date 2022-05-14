package com.example.eventlist.data.network

import com.example.eventlist.data.model.EventResponse
import retrofit2.http.GET

interface EventApi {
    @GET("/v3/events")
    suspend fun getAll(): EventResponse
}