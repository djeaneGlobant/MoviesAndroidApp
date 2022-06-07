package com.example.movies.data.repository

import com.example.networkmodule.api.BusinessApi
import com.example.networkmodule.model.DataState
import com.example.networkmodule.model.EventData
import javax.inject.Inject

interface EventsRepository {
    suspend fun getEvents(): DataState<EventData>
}

class EventsRepositoryImpl @Inject constructor(
    private val api: BusinessApi
): EventsRepository {

    override suspend fun getEvents(): DataState<EventData> =
        api.getEvents()
}