package com.example.movies.domain

import com.example.movies.data.repository.PopularMoviesRepository
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(
    private val repository: PopularMoviesRepository
) {

    fun invoke(){
        repository.getPopularMovies()
    }
}