package com.example.eventlist.domain.usecase.business

import com.example.eventlist.data.repository.BusinessRepository
import javax.inject.Inject

interface ToggleFavorite {
    suspend fun invoke(id: String, isFavorite: Boolean)
}

class ToggleFavoriteImpl @Inject constructor(
    private val repository: BusinessRepository
): ToggleFavorite {
    override suspend fun invoke(id: String, isFavorite: Boolean) {
        repository.toggleFavorite(id, isFavorite)
    }

}