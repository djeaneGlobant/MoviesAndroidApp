package com.example.networkmodule.api

import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("popular")
    fun getPopularMovies(@Query("api_key") apiKey: String)
}