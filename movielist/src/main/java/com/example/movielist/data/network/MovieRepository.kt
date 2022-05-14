package com.example.movielist.data.network

import com.example.movielist.data.model.MovieModel
import javax.inject.Inject

interface IMovieRepository {
    suspend fun getAllMovies(): List<MovieModel>
}

class MovieRepository @Inject constructor(): IMovieRepository {
    private val helper = RetrofitHelper.getInstance().create(MovieApi::class.java)

    override suspend fun getAllMovies(): List<MovieModel> = helper.getAllMovies()
}