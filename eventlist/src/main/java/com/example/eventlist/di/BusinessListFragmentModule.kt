package com.example.eventlist.di

import androidx.lifecycle.ViewModel
import com.example.base.activity.ActivityScope
import com.example.base.viewmodel.ViewModelKey
import com.example.eventlist.data.repository.BusinessRepository
import com.example.eventlist.data.repository.BusinessRepositoryImpl
import com.example.eventlist.domain.usecase.business.*
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
    abstract fun bindGetBusiness(useCaseImpl: GetBusinessImpl): GetBusiness

    @Binds
    abstract fun bindGetLocations(useCaseImpl: GetLocationsImpl): GetLocations

    @Binds
    abstract fun bindGetCategories(useCaseImpl: GetCategoriesImpl): GetCategories

    @Binds
    abstract fun bindToggleFavorite(useCaseImpl: ToggleFavoriteImpl): ToggleFavorite

    @Binds
    @IntoMap
    @ViewModelKey(BusinessListViewModel::class)
    abstract fun bindBusinessListViewModel(viewModel: BusinessListViewModel): ViewModel
}