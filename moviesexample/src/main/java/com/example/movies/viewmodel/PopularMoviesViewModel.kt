package com.example.movies.viewmodel

import androidx.lifecycle.ViewModel
import com.example.movies.domain.PopularMoviesUseCase
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
    private val getPopularMovies: PopularMoviesUseCase
) : ViewModel() {

    fun getMovies() {
        getPopularMovies.invoke()
    }
}