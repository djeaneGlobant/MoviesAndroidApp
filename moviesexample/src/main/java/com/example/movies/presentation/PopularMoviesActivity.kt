package com.example.movies.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.base.viewmodel.ViewModelFactory
import com.example.movies.R
import com.example.movies.viewmodel.PopularMoviesViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class PopularMoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: PopularMoviesViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_popular_movies)
        callApi()
    }

    private fun callApi() {
        //viewModel.getBusinessByFoodAndLocation()
        viewModel.getEvents()
    }
}