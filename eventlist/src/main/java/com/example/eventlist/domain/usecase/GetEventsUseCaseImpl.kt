package com.example.eventlist.domain.usecase

import com.example.eventlist.data.network.DataState
import com.example.eventlist.data.network.EventRepository
import com.example.eventlist.domain.model.Event
import javax.inject.Inject

interface GetEventsUseCase {
    suspend fun invoke(query: String?): DataState<List<Event>>
}

class GetEventsUseCaseImpl @Inject constructor(
    private val repository: EventRepository
): GetEventsUseCase {

    override suspend operator fun invoke(query: String?): DataState<List<Event>> = repository.getAll(query)
}