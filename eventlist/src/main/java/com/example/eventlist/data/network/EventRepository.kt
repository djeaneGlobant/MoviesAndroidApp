package com.example.eventlist.data.network

import com.example.eventlist.data.database.EventDataBase
import com.example.eventlist.domain.model.Event
import com.example.eventlist.domain.model.toDomain
import com.example.eventlist.domain.model.toEntity
import kotlinx.coroutines.delay
import javax.inject.Inject

interface IEventRepository {
    suspend fun getAll(query: String?): DataState<List<Event>>
    fun setCurrent(event: Event?)
    fun getCurrent(): Event?
    suspend fun toggleFavorite(id: String, isFavorite: Boolean)
}

class EventRepository @Inject constructor(
    private val helper: EventApi,
    private val db: EventDataBase
) : IEventRepository {
    private var current: Event? = null

    override suspend fun getAll(query: String?): DataState<List<Event>> {
        val dbEvents = db.eventDao().getAll()
        if (dbEvents.isNotEmpty()) {
            return DataState.Success(dbEvents.map { it.toDomain() })
        }
        delay(4000)
        try {
            val events = helper.getAll().results.map { it.toDomain() }
            db.eventDao().insertAll(events.map { it.toEntity() })
            if (!query.isNullOrEmpty()) {
                val result = events.filter {
                    it.location.toString().lowercase().contains(query.lowercase())
                }
                return DataState.Success(result)
            }
            return DataState.Success(events)
        } catch (e: Exception) {
            return DataState.Failure(e)
        }
    }

    override fun setCurrent(event: Event?) {
        current = event
    }

    override fun getCurrent(): Event? = current

    override suspend fun toggleFavorite(id: String, isFavorite: Boolean) {
        db.eventDao().updateFavorite(id, isFavorite)
    }
}

