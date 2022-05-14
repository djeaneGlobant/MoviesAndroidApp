package com.example.eventlist.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.base.activity.ActivityScope
import com.example.base.viewmodel.ViewModelKey
import com.example.eventlist.data.network.IEventRepository
import com.example.eventlist.data.network.EventRepository
import com.example.eventlist.domain.usecase.GetEventsUseCase
import com.example.eventlist.domain.usecase.IGetEventsUseCase
import com.example.eventlist.domain.usecase.IToggleFavoriteUseCase
import com.example.eventlist.domain.usecase.ToggleFavoriteUseCase
import com.example.eventlist.presentation.eventlist.EventListFragment
import com.example.eventlist.presentation.eventlist.EventListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class EventListContributeModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            EventListModule::class, NetworkModule::class, EventDbModule::class
        ]
    )
    abstract fun bindFragment(): EventListFragment
}

@Module
private abstract class EventListModule {
    @Binds
    abstract fun bindRepository(repository: EventRepository): IEventRepository

    @Binds
    abstract fun bindGetMoviesUseCase(getMoviesUseCase: GetEventsUseCase): IGetEventsUseCase

    @Binds
    abstract fun bindToggleFavoriteUseCase(toggleFavoriteUseCase: ToggleFavoriteUseCase): IToggleFavoriteUseCase

    @Binds
    @IntoMap
    @ViewModelKey(EventListViewModel::class)
    abstract fun bindViewModel(viewModel: EventListViewModel): ViewModel
}