package com.example.movielist.domain

import com.example.movielist.data.model.toDomain
import com.example.movielist.data.network.MovieRepository
import com.example.movielist.domain.model.Movie
import javax.inject.Inject

interface IGetMoviesUseCase {
    suspend fun invoke(): List<Movie>
}

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
): IGetMoviesUseCase {

    override suspend operator fun invoke(): List<Movie> = repository.getAllMovies().map { it.toDomain() }
}