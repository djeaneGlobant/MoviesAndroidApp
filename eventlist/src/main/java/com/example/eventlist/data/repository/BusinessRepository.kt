package com.example.eventlist.data.repository

import com.example.eventlist.domain.model.Business
import com.example.eventlist.domain.model.toDomain
import com.example.eventlist.util.DataState
import com.example.localstorage.database.EventDataBase
import com.example.networkmodule.api.BusinessApi
import javax.inject.Inject

interface BusinessRepository {
    suspend fun getBusiness(term: String, location: String): DataState<List<Business>>

    suspend fun getCategories(): DataState<List<String>>

    suspend fun getLocations(): DataState<List<String>>
}

class BusinessRepositoryImpl @Inject constructor(
    private val api: BusinessApi,
    private val db: EventDataBase
): BusinessRepository {
    override suspend fun getBusiness(term: String, location: String): DataState<List<Business>> {
        return try {
            val response = api.getBusinessByFoodAndLocation(term, location).search.business?.map { it.toDomain() }
            DataState.Success(response)
        } catch (e: Exception) {
            DataState.Failure(e)
        }

    }

    override suspend fun getCategories(): DataState<List<String>> {
        return try {
            val response =
                api.getCategories().data.categories.map { it.parentCategories.first().title }
            DataState.Success(response)
        } catch (e: Exception) {
            DataState.Failure(e)
        }
    }

    override suspend fun getLocations(): DataState<List<String>> {
        return try {
            val response = api.getEventLocations().eventSearch.events?.map { it.location?.toDomain()?.city!! }
            DataState.Success(response)
        } catch (e: Exception) {
            DataState.Failure(e)
        }
    }

}