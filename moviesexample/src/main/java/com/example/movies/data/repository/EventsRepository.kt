package com.example.movies.data.repository

import com.example.networkmodule.api.BusinessApi
import com.example.networkmodule.model.EventData
import javax.inject.Inject

interface EventsRepository {
    suspend fun getEvents(location: String?): EventData
}

class EventsRepositoryImpl @Inject constructor(
    private val api: BusinessApi
): EventsRepository {

    override suspend fun getEvents(location: String?): EventData =
        api.getEvents(location)
}