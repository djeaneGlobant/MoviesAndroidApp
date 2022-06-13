package com.example.eventlist.domain.usecase.business

import com.example.eventlist.data.repository.BusinessRepository
import com.example.eventlist.util.DataState
import javax.inject.Inject

interface GetCategories {
    suspend fun invoke(): DataState<List<String>>
}

class GetCategoriesImpl @Inject constructor(
    private val repository: BusinessRepository
) : GetCategories{
    override suspend fun invoke(): DataState<List<String>> {
        return repository.getCategories()
    }

}