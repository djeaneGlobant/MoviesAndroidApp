package com.example.movies.di

import com.example.movies.PopularMoviesActivity
import com.example.movies.data.repository.PopularMoviesRepository
import com.example.movies.data.repository.PopularMoviesRepositoryImpl
import com.example.movies.domain.PopularMoviesUseCase
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesContributeModule {

    @ContributesAndroidInjector(modules = [MoviesModule::class])
    abstract fun provideActivity(): PopularMoviesActivity
}

@Module
abstract class MoviesModule {

    @Binds
    abstract fun provideRepository(repository: PopularMoviesRepositoryImpl): PopularMoviesRepository

    @Binds
    abstract fun provideUseCase(useCase: PopularMoviesUseCase): PopularMoviesUseCase
}