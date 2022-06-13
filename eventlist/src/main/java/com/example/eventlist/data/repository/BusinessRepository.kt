package com.example.eventlist.data.repository

import com.example.eventlist.domain.model.Business
import com.example.eventlist.domain.model.toDomain
import com.example.eventlist.util.DataState
import com.example.localstorage.database.EventDataBase
import com.example.localstorage.entity.BusinessEntity
import com.example.networkmodule.api.BusinessApi
import javax.inject.Inject

interface BusinessRepository {
    suspend fun getBusiness(term: String, location: String): DataState<List<Business>>

    suspend fun getCategories(): DataState<List<String>>

    suspend fun getLocations(): DataState<List<String>>

    suspend fun toggleFavorite(id: String, isFavorite: Boolean)
}

class BusinessRepositoryImpl @Inject constructor(
    private val api: BusinessApi,
    private val db: EventDataBase
) : BusinessRepository {
    override suspend fun getBusiness(term: String, location: String): DataState<List<Business>> {
        return try {
            val dbResponse = db.businessDao().getAll()
            val response = api.getBusinessByFoodAndLocation(
                term,
                location
            ).search.business?.map { it.toDomain() }
            if(dbResponse.isNotEmpty()) {
                response?.forEach { business ->
                    dbResponse.firstOrNull { dbBusiness -> dbBusiness.id == business.id }?.let { dbBusiness ->
                        business.isFavorite = dbBusiness.isFavorite
                    }
                }
            }
            DataState.Success(response)
        } catch (e: Exception) {
            DataState.Failure(e)
        }

    }

    override suspend fun getCategories(): DataState<List<String>> {
        return try {
            val response = api.getCategories().data.categories
            val result =
                response.mapNotNull { it.parentCategories.firstOrNull()?.title }.toSet().toList()
            DataState.Success(result)
        } catch (e: Exception) {
            DataState.Failure(e)
        }
    }

    override suspend fun getLocations(): DataState<List<String>> {
        return try {
            val response =
                api.getEventLocations().eventSearch.events
            val result = response?.mapNotNull { it.location?.toDomain()?.city }?.toSet()?.toList()
            DataState.Success(result)
        } catch (e: Exception) {
            DataState.Failure(e)
        }
    }

    override suspend fun toggleFavorite(id: String, isFavorite: Boolean) {
        db.businessDao().insert(BusinessEntity(id, isFavorite))
    }

}