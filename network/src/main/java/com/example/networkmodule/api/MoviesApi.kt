package com.example.networkmodule.api

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): MoviesResponse
}


data class MoviesResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val postPath: String?,
    @SerializedName("original_title") val originalTitle: String
)