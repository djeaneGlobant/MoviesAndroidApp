package com.example.eventlist.domain.usecase.business

import com.example.eventlist.data.repository.BusinessRepository
import com.example.eventlist.domain.model.Business
import com.example.eventlist.util.DataState
import javax.inject.Inject

interface GetBusiness {
    suspend fun invoke(term: String, location: String): DataState<List<Business>>
}

class GetBusinessImpl @Inject constructor(
    private val repository: BusinessRepository
): GetBusiness {
    override suspend fun invoke(term: String, location: String): DataState<List<Business>> {
        return repository.getBusiness(term, location)
    }

}