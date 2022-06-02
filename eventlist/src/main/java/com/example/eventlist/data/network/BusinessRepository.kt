package com.example.eventlist.data.network

import com.example.eventlist.domain.model.Business
import com.example.eventlist.domain.model.toDomain
import com.example.networkmodule.api.BusinessApi
import javax.inject.Inject

interface BusinessRepository {
    suspend fun getBusinessByFoodAndLocation(term: String, location: String): DataState<List<Business>>
}

class BusinessRepositoryImpl @Inject constructor(
    private val api: BusinessApi,
): BusinessRepository {
    override suspend fun getBusinessByFoodAndLocation(term: String, location: String): DataState<List<Business>> {
        return try {
            val businessResult = api.getBusinessByFoodAndLocation(term, location)?.search?.business?.map { it.toDomain() }
            DataState.Success(businessResult)

        } catch (e: Exception) {
            DataState.Failure(e)
        }
    }

}