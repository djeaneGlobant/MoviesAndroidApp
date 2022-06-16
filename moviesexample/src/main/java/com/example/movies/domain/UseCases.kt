package com.example.movies.domain

import com.example.movies.data.repository.BusinessRepository
import com.example.movies.data.repository.EventsRepository
import com.example.networkmodule.model.BusinessData
import com.example.networkmodule.model.EventData
import javax.inject.Inject

interface UseCases {

    suspend fun businessUseCase(
        foodType: String,
        location: String
    ) : BusinessData

    suspend fun eventUseCase(location: String?): EventData
}

class UseCasesImpl @Inject constructor(
    private val businessRepository: @JvmSuppressWildcards BusinessRepository,
    private val eventsRepository: @JvmSuppressWildcards EventsRepository
): UseCases {

    override suspend fun businessUseCase(
        foodType: String,
        location: String
    ): BusinessData =
        businessRepository.getBusinessByFoodAndLocation(foodType, location)

    override suspend fun eventUseCase(location: String?): EventData =
        eventsRepository.getEvents(location)
}