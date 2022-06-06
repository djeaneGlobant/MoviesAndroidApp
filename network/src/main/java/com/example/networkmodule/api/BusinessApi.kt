package com.example.networkmodule.api

import com.example.networkmodule.model.EventData
import com.example.networkmodule.model.BusinessData
import com.example.networkmodule.model.DataState


interface BusinessApi {

    suspend fun getBusinessByFoodAndLocation(term: String, location: String): DataState<BusinessData>

    suspend fun getEvents(): DataState<EventData>
}
