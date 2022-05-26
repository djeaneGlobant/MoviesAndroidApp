package com.example.eventlist.data.network

import com.example.eventlist.data.database.EventDataBase
import com.example.eventlist.data.database.entity.EventEntity
import com.example.eventlist.domain.model.*
import kotlinx.coroutines.delay
import javax.inject.Inject

interface EventRepository {
    suspend fun getAll(query: String?): DataState<List<Event>>
    fun setCurrent(event: Event?)
    fun getCurrent(): Event?
    suspend fun toggleFavorite(id: String, isFavorite: Boolean)
}

class EventRepositoryImpl @Inject constructor(
    private val helper: EventApi,
    private val db: EventDataBase
) : EventRepository {
    private var current: Event? = null

    override suspend fun getAll(query: String?): DataState<List<Event>> {
        delay(2000)
        return try {
            val dbEvents = db.eventDao().getAll()
            val events = helper.getAll(query).results.toDomainList().map { event ->
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
}

