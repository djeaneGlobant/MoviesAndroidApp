package com.example.eventlist.data.network

import com.example.eventlist.domain.model.*
import com.example.localstorage.database.EventDataBase
import com.example.localstorage.entity.EventEntity
import kotlinx.coroutines.delay
import javax.inject.Inject

interface EventRepository {
    suspend fun getAll(query: String?): DataState<List<Event>>
    fun setCurrent(event: Event?)
    fun getCurrent(): Event?
    suspend fun toggleFavorite(id: String, isFavorite: Boolean)
    suspend fun getLocations(): DataState<List<String>>
}

class EventRepositoryImpl @Inject constructor(
    private val api: EventApi,
    private val db: EventDataBase
) : EventRepository {
    private var current: Event? = null

    override suspend fun getAll(query: String?): DataState<List<Event>> {
        delay(2000)
        return try {
            val dbEvents = db.eventDao().getAll()
            val events = api.getAll(query).results.toDomainList().map { event ->
                dbEvents.firstOrNull { dbEvent -> dbEvent.id == event.id }?.let { dbEvent ->
                    event.isFavorite = dbEvent.isFavorite
                    event
                } ?: event
            }
            DataState.Success(events.searchQuery(query))
        } catch (e: Exception) {
            DataState.Failure(e)
        }
    }

    private fun List<Event>.searchQuery(query: String?): List<Event> {
        return query?.let {
            filter {
                it.location.toString().lowercase().contains(query.lowercase())
            }
        } ?: this
    }

    override fun setCurrent(event: Event?) {
        current = event
    }

    override fun getCurrent(): Event? = current

    override suspend fun toggleFavorite(id: String, isFavorite: Boolean) {
        db.eventDao().insert(EventEntity(id, isFavorite))
    }

    override suspend fun getLocations(): DataState<List<String>> {
        val locations = api.getLocations().results.map { it.toDomain().location.city }.toSet().toList()
        return DataState.Success(locations)
    }
}

