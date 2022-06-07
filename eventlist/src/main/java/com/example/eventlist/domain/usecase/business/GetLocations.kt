package com.example.eventlist.domain.usecase.business

import com.example.eventlist.data.repository.BusinessRepository
import com.example.eventlist.domain.model.Business
import com.example.eventlist.util.DataState
import javax.inject.Inject

interface GetLocations {
    suspend fun invoke(): DataState<List<String>>
}

class GetLocationsImpl @Inject constructor(
    private val repository: BusinessRepository
): GetLocations {
    override suspend fun invoke(): DataState<List<String>> {
        return repository.getLocations()
    }

}