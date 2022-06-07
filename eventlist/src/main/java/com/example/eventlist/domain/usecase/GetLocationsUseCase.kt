package com.example.eventlist.domain.usecase

import com.example.networkmodule.model.DataState
import com.example.eventlist.data.network.EventRepository
import javax.inject.Inject

interface GetLocationsUseCase {
    suspend fun invoke(): DataState<List<String>>
}

class GetLocationsUseCaseImpl @Inject constructor(
    private val repository: EventRepository
): GetLocationsUseCase {

    override suspend operator fun invoke(): DataState<List<String>> {
        return repository.getLocations()
    }

}