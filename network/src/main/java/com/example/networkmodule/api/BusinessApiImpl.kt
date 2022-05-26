package com.example.networkmodule.api

import com.apollographql.apollo3.ApolloClient
import com.example.network.GetEventsQuery
import com.example.network.SearchByFoodAndPlaceQuery
import com.example.networkmodule.model.EventData
import com.example.networkmodule.model.BusinessData
import com.example.networkmodule.util.mapToObject
import com.google.gson.Gson

class BusinessApiImpl(
    private val apolloClient: ApolloClient,
    private val gson: Gson
) : BusinessApi {

    companion object {
        private const val LIMIT_EVENTS = 100
    }

    override suspend fun getBusinessByFoodAndLocation(term: String, location: String): BusinessData? {
        val response = apolloClient.query(SearchByFoodAndPlaceQuery(term, location)).execute()
        return if (response.hasErrors().not()) {
            gson.mapToObject(response.data)
        } else null

    }

    override suspend fun getEvents(): EventData? {
        val response = apolloClient.query(GetEventsQuery(LIMIT_EVENTS)).execute()
        return if (response.hasErrors().not()) {
            gson.mapToObject(response.data)
        } else null
    }
}