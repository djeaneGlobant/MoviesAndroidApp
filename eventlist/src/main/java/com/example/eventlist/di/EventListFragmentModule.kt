package com.example.eventlist.di

import androidx.lifecycle.ViewModel
import com.example.base.activity.ActivityScope
import com.example.base.viewmodel.ViewModelKey
import com.example.eventlist.data.network.EventRepository
import com.example.eventlist.data.network.EventRepositoryImpl
import com.example.eventlist.domain.usecase.*
import com.example.eventlist.presentation.eventlist.EventListFragment
import com.example.eventlist.presentation.eventlist.EventListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class EventListContributeModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            EventListFragmentModule::class
        ]
    )
    abstract fun bindFragment(): EventListFragment
}

@Module
private abstract class EventListFragmentModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: EventRepositoryImpl): EventRepository

    @Binds
    abstract fun bindGetMoviesUseCase(getMoviesUseCaseImpl: GetEventsUseCaseImpl): GetEventsUseCase

    @Binds
    abstract fun bindToggleFavoriteUseCase(toggleFavoriteUseCaseImpl: ToggleFavoriteUseCaseImpl): ToggleFavoriteUseCase

    @Binds
    abstract fun bindGetLocationsUseCase(getLocationsUseCase: GetLocationsUseCaseImpl): GetLocationsUseCase

    @Binds
    @IntoMap
    @ViewModelKey(EventListViewModel::class)
    abstract fun bindViewModel(viewModel: EventListViewModel): ViewModel
}