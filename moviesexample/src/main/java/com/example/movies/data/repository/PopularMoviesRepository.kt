package com.example.movies.data.repository

import com.example.networkmodule.api.MoviesApi
import javax.inject.Inject

interface PopularMoviesRepository{
   fun getPopularMovies()
}

class PopularMoviesRepositoryImpl @Inject constructor(
    private val api: MoviesApi
): PopularMoviesRepository {
    override fun getPopularMovies() {
        api.getPopularMovies("0266c46c25aa2fd93373aba4f48e0fe8")
    }
}