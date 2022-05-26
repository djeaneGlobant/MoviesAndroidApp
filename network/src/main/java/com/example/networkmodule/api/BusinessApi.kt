package com.example.networkmodule.api

import com.example.networkmodule.model.EventData
import com.example.networkmodule.model.BusinessData


interface BusinessApi {

    suspend fun getBusinessByFoodAndLocation(term: String, location: String): BusinessData?

    suspend fun getEvents(): EventData?
}
