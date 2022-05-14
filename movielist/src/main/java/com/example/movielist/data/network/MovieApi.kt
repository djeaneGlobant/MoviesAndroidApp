package com.example.movielist.data.network

import com.example.movielist.data.model.MovieModel
import retrofit2.http.GET

internal interface MovieApi {
    @GET("/movies")
    suspend fun getAllMovies(): List<MovieModel>
}