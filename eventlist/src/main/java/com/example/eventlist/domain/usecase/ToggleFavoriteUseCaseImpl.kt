package com.example.eventlist.domain.usecase

import com.example.eventlist.data.network.EventRepository
import javax.inject.Inject

interface ToggleFavoriteUseCase {
    suspend fun invoke(id: String, isFavorite: Boolean)
}

class ToggleFavoriteUseCaseImpl @Inject constructor(
    private val repository: EventRepository
): ToggleFavoriteUseCase {
    override suspend operator fun invoke(id: String, isFavorite: Boolean) {
        repository.toggleFavorite(id, isFavorite)
    }
}