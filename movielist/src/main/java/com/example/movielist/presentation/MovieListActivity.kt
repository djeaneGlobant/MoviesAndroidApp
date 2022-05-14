package com.example.movielist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.base.viewmodel.ViewModelFactory
import com.example.movielist.R
import dagger.android.AndroidInjection
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val model: MoviesViewModel by viewModels { viewModelFactory }
    private lateinit var rvMovies: RecyclerView
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_movie_list)

        rvMovies = findViewById(R.id.rvMovies)

//        model = ViewModelProvider(this)[MoviesViewModel::class.java]

        model.getMovies()

        rvMovies.layoutManager = LinearLayoutManager(this)
        model.movies.observe(this) {
            adapter = MoviesAdapter(it) { id ->
                selectCurrentMovie(id)
            }
            rvMovies.adapter = adapter
        }
    }

    private fun selectCurrentMovie(id: Int) {
        model.selectMovie(id)
    }
}