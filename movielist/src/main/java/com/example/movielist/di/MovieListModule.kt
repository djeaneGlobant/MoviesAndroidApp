package com.example.movielist.di

import androidx.lifecycle.ViewModel
import com.example.base.activity.ActivityScope
import com.example.base.viewmodel.ViewModelKey
import com.example.movielist.data.network.IMovieRepository
import com.example.movielist.data.network.MovieRepository
import com.example.movielist.domain.GetMoviesUseCase
import com.example.movielist.domain.IGetMoviesUseCase
import com.example.movielist.presentation.MovieListActivity
import com.example.movielist.presentation.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class MovieListContributeModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MovieListModule::class])
    abstract fun bindActivity(): MovieListActivity
}

@Module
abstract class MovieListModule {
    @Binds
    abstract fun bindRepository(repository: MovieRepository): IMovieRepository

    @Binds
    abstract fun bindGetMoviesUseCase(getMoviesUseCase: GetMoviesUseCase): IGetMoviesUseCase

    @Binds
    @IntoMap
    @Singleton
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindViewModel(viewModel: MoviesViewModel): ViewModel
}