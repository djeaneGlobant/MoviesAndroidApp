package com.example.movies.di

import androidx.lifecycle.ViewModel
import com.example.base.activity.ActivityScope
import com.example.base.viewmodel.ViewModelKey
import com.example.movies.data.repository.PopularMoviesRepository
import com.example.movies.data.repository.PopularMoviesRepositoryImpl
import com.example.movies.domain.PopularMoviesUseCase
import com.example.movies.domain.PopularMoviesUseCaseImpl
import com.example.movies.presentation.PopularMoviesActivity
import com.example.movies.viewmodel.PopularMoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class MoviesContributeModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MoviesModule::class])
    abstract fun bindActivity(): PopularMoviesActivity
}

@Module
abstract class MoviesModule {

    @Binds
    abstract fun bindRepository(repository: PopularMoviesRepositoryImpl): PopularMoviesRepository

    @Binds
    abstract fun bindUseCase(userCase: PopularMoviesUseCaseImpl): PopularMoviesUseCase

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel::class)
    abstract fun bindViewModel(viewModel: PopularMoviesViewModel): ViewModel
}