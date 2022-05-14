package com.example.eventlist.domain.usecase

import com.example.eventlist.data.network.EventRepository
import javax.inject.Inject

interface IToggleFavoriteUseCase {
    suspend fun invoke(id: String, isFavorite: Boolean)
}

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: EventRepository
): IToggleFavoriteUseCase {
    override suspend operator fun invoke(id: String, isFavorite: Boolean) {
        repository.toggleFavorite(id, isFavorite)
    }
}