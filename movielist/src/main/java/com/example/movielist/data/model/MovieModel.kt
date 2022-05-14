package com.example.movielist.data.model

import com.example.movielist.domain.model.Movie
import com.google.gson.annotations.SerializedName

class MovieModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val postPath: String?,
    @SerializedName("original_title")
    val originalTitle: String
)

fun MovieModel.toDomain() = Movie(id, postPath, originalTitle)