package com.example.eventlist.data.repository

import com.example.eventlist.domain.model.*
import com.example.networkmodule.api.BusinessApi
import com.example.localstorage.database.EventDataBase
import com.example.localstorage.entity.EventEntity
import com.example.eventlist.util.DataState
import javax.inject.Inject

interface EventRepository {
    suspend fun getAll(query: String?): DataState<List<Event>>
    fun setCurrent(event: Event?)
    fun getCurrent(): Event?
    suspend fun toggleFavorite(id: String, isFavorite: Boolean)
    suspend fun getLocations(): DataState<List<String>>
}

class EventRepositoryImpl @Inject constructor(
    private val api: BusinessApi,
    private val db: EventDataBase
) : EventRepository {
    private var current: Event? = null


    override suspend fun getAll(query: String?): DataState<List<Event>> {
        return try {
            val dbEvents = db.eventDao().getAll()
            val events = api.getEvents(query).eventSearch.events?.map { it.toDomain() }
            if (dbEvents.isNotEmpty()) {
                events?.forEach { event ->
                    dbEvents.firstOrNull { dbEvent -> dbEvent.id == event.id }?.let { dbEvent ->
                        event.isFavorite = dbEvent.isFavorite
                    }
                }
            }
            DataState.Success(events)
        } catch (e: Exception) {
            DataState.Failure(e)
        }
    }

    override fun setCurrent(event: Event?) {
        current = event
    }

    override fun getCurrent(): Event? = current

    override suspend fun toggleFavorite(id: String, isFavorite: Boolean) {
        db.eventDao().insert(EventEntity(id, isFavorite))
    }

    override suspend fun getLocations(): DataState<List<String>> {
        return try {
            val locations =
                api.getEventLocations().eventSearch.events?.map { it.toDomain().location.city }
                    ?.toSet()
                    ?.toList()
            DataState.Success(locations ?: emptyList())
        } catch (e: Exception) {
            DataState.Failure(e)
        }
    }
}

