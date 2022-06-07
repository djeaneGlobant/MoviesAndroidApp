package com.example.networkmodule.api

import com.apollographql.apollo3.ApolloClient
import com.example.network.GetCategoriesQuery
import com.example.network.GetEventLocationsQuery
import com.example.network.GetEventsQuery
import com.example.network.SearchByFoodAndPlaceQuery
import com.example.networkmodule.model.EventData
import com.example.networkmodule.model.BusinessData
import com.example.networkmodule.model.CategoryData
import com.example.networkmodule.util.mapToObject
import com.google.gson.Gson

class BusinessApiImpl(
    private val apolloClient: ApolloClient,
    private val gson: Gson
) : BusinessApi {

    companion object {
        private const val LIMIT_EVENTS = 100
    }

    override suspend fun getBusinessByFoodAndLocation(term: String, location: String): BusinessData {
        val response = apolloClient.query(SearchByFoodAndPlaceQuery(term, location)).execute()
        return if (response.hasErrors().not()) {
            gson.mapToObject(response.data)
        } else {
            throw Exception(response.errors?.first()?.message ?: "")
        }
    }

    override suspend fun getEvents(): EventData {
        val response = apolloClient.query(GetEventsQuery(LIMIT_EVENTS)).execute()
        return if (response.hasErrors().not()) {
            gson.mapToObject(response.data)
        } else {
            throw Exception(response.errors?.first()?.message ?: "")
        }
    }

    override suspend fun getEventLocations(): EventData {
        val response = apolloClient.query(GetEventLocationsQuery(LIMIT_EVENTS)).execute()
        return if(response.hasErrors().not()) {
            gson.mapToObject(response.data)
        } else {
            throw Exception(response.errors?.first()?.message ?: "has occurred an error")
        }
    }

    override suspend fun getCategories(): CategoryData {
        val response = apolloClient.query(GetCategoriesQuery()).execute()
        return if(response.hasErrors().not()) {
            gson.mapToObject(response.data)
        } else {
            throw Exception(response.errors?.first()?.message ?: "has occurred an error")
        }
    }

}