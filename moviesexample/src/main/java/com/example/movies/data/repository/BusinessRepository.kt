package com.example.movies.data.repository

import com.example.networkmodule.api.BusinessApi
import com.example.networkmodule.model.BusinessData
import javax.inject.Inject

interface BusinessRepository {
   suspend fun getBusinessByFoodAndLocation(
       foodType: String,
       location: String
   ): BusinessData?
}

class BusinessRepositoryImpl @Inject constructor(
    private val api: BusinessApi
): BusinessRepository {

    override suspend fun getBusinessByFoodAndLocation(
        foodType: String,
        location: String
    ): BusinessData? =
        api.getBusinessByFoodAndLocation(foodType, location)
}