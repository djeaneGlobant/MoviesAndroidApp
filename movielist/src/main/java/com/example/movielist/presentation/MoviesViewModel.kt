package com.example.movielist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielist.domain.GetMoviesUseCase
import com.example.movielist.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel() {
    val movies: MutableLiveData<List<Movie>> = MutableLiveData(emptyList())
    var selected: Movie? = null


    fun getMovies() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                getMoviesUseCase()
            }
            movies.value = response
        }
    }

    fun selectMovie(id: Int) {
        selected = movies.value?.firstOrNull { it.id == id }
        println("selected: $selected")
    }

}