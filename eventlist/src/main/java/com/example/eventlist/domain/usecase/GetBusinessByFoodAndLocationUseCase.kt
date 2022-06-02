package com.example.eventlist.domain.usecase

import com.example.eventlist.data.network.BusinessRepository
import com.example.eventlist.data.network.DataState
import com.example.eventlist.domain.model.Business
import javax.inject.Inject

interface GetBusinessByFoodAndLocationUseCase {
    suspend fun invoke(term: String, location: String): DataState<List<Business>>
}

class GetBusinessByFoodAndLocationsUseCaseImpl @Inject constructor(
    private val repository: BusinessRepository
): GetBusinessByFoodAndLocationUseCase {
    override suspend fun invoke(term: String, location: String): DataState<List<Business>> {
        return repository.getBusinessByFoodAndLocation(term, location)
    }

}