package com.example.movies.di

import androidx.lifecycle.ViewModel
import com.example.base.activity.ActivityScope
import com.example.base.viewmodel.ViewModelKey
import com.example.movies.data.repository.BusinessRepository
import com.example.movies.data.repository.BusinessRepositoryImpl
import com.example.movies.data.repository.EventsRepository
import com.example.movies.data.repository.EventsRepositoryImpl
import com.example.movies.domain.UseCases
import com.example.movies.domain.UseCasesImpl
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
    abstract fun bindBusinessRepository(repository: BusinessRepositoryImpl): BusinessRepository

    @Binds
    abstract fun bindEventsRepository(repository: EventsRepositoryImpl): EventsRepository

    @Binds
    abstract fun bindUseCase(userCase: UseCasesImpl): UseCases

    @Binds
    @IntoMap
    @ViewModelKey(PopularMoviesViewModel::class)
    abstract fun bindViewModel(viewModel: PopularMoviesViewModel): ViewModel
}