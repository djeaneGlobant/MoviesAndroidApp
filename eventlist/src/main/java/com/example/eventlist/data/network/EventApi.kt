package com.example.eventlist.data.network

import com.example.eventlist.data.model.EventResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EventApi {
    @GET("/v3/events")
    suspend fun getAll(@Query("location") location: String?, @Query("limit") limit: Int = 20): EventResponse

    @GET("/v3/events")
    suspend fun getLocations(@Query("limit") limit: Int = 20): EventResponse
}