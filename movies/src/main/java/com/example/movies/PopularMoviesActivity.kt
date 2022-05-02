package com.example.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.viewmodel.PopularMoviesViewModel

class PopularMoviesActivity : AppCompatActivity() {

    private lateinit var viewModel: PopularMoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_movies)

        callApi()
    }

    private fun callApi(){
        runOnUiThread {
        }
    }
}