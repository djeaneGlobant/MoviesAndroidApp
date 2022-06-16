package com.example.moviesandroidapp.application.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun provideApplicationContext(app: Application): Context = app.applicationContext
}