package com.example.moviesandroidapp.application.di

import android.app.Application
import com.example.base.viewmodel.di.ViewModelModule
import com.example.eventlist.di.EventListModule
import com.example.localstorage.di.LocalStorageModule
import com.example.moviesandroidapp.application.MoviesAndroidApp
import com.example.networkmodule.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        FeatureModule::class,
        ViewModelModule::class,
        EventListModule::class,
        LocalStorageModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MoviesAndroidApp> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}