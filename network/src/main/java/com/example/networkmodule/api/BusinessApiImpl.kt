package com.example.networkmodule.api

import com.apollographql.apollo3.ApolloClient
import com.example.network.GetEventsQuery
import com.example.network.SearchByFoodAndPlaceQuery
import com.example.networkmodule.model.EventData
import com.example.networkmodule.model.BusinessData
import com.example.networkmodule.model.DataState
import com.example.networkmodule.util.mapToObject
import com.google.gson.Gson

class BusinessApiImpl(
    private val apolloClient: ApolloClient,
    private val gson: Gson
) : BusinessApi {

    companion object {
        private const val LIMIT_EVENTS = 100
    }

    override suspend fun getBusinessByFoodAndLocation(term: String, location: String): DataState<BusinessData> {
        val response = apolloClient.query(SearchByFoodAndPlaceQuery(term, location)).execute()
        return if (response.hasErrors().not()) {
            DataState.Success(gson.mapToObject(response.data))
        } else {
            DataState.Failure(Exception(response.errors?.first()?.message ?: ""))
        }
    }

    override suspend fun getEvents(): DataState<EventData> {
        val response = apolloClient.query(GetEventsQuery(LIMIT_EVENTS)).execute()
        return if (response.hasErrors().not()) {
            DataState.Success(gson.mapToObject(response.data))
        } else {
            DataState.Failure(Exception(response.errors?.first()?.message ?: ""))
        }
    }
}