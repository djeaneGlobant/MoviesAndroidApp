package com.example.movies.domain

import com.example.movies.data.repository.PopularMoviesRepository
import javax.inject.Inject

interface PopularMoviesUseCase {
    operator fun invoke()
}

class PopularMoviesUseCaseImpl @Inject constructor(
    private val repository: @JvmSuppressWildcards PopularMoviesRepository
) : PopularMoviesUseCase {

    override fun invoke() {
        repository.getPopularMovies()
    }
}