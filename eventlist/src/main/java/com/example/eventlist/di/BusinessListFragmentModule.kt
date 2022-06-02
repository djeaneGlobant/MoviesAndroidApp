package com.example.eventlist.di

import androidx.lifecycle.ViewModel
import com.example.base.activity.ActivityScope
import com.example.base.viewmodel.ViewModelKey
import com.example.eventlist.data.network.BusinessRepository
import com.example.eventlist.data.network.BusinessRepositoryImpl
import com.example.eventlist.domain.usecase.GetBusinessByFoodAndLocationUseCase
import com.example.eventlist.domain.usecase.GetBusinessByFoodAndLocationsUseCaseImpl
import com.example.eventlist.presentation.businesslist.BusinessListFragment
import com.example.eventlist.presentation.businesslist.BusinessListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class BusinessListContributeModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            BusinessListFragmentModule::class
        ]
    )
    abstract fun bindFragment(): BusinessListFragment
}

@Module
private abstract class BusinessListFragmentModule {
    @Binds
    abstract fun bindRepository(repositoryImpl: BusinessRepositoryImpl): BusinessRepository

    @Binds
    abstract fun bindGetBusinessByFoodAndLocationUseCase(useCaseImpl: GetBusinessByFoodAndLocationsUseCaseImpl): GetBusinessByFoodAndLocationUseCase

    @Binds
    @IntoMap
    @ViewModelKey(BusinessListViewModel::class)
    abstract fun bindBusinessListViewModel(viewModel: BusinessListViewModel): ViewModel
}