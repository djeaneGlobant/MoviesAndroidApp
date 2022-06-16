package com.example.networkmodule.api

import com.example.networkmodule.model.EventData
import com.example.networkmodule.model.BusinessData
import com.example.networkmodule.model.CategoryData

interface BusinessApi {

    suspend fun getBusinessByFoodAndLocation(term: String, location: String): BusinessData

    suspend fun getEvents(location: String?): EventData

    suspend fun getEventLocations(): EventData

    suspend fun getCategories(): CategoryData
}
