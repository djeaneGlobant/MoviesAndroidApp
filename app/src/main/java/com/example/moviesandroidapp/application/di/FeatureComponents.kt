package com.example.moviesandroidapp.application.di

import com.example.movies.di.MoviesContributeModule
import dagger.Module


@Module(
    includes = [
        MoviesContributeModule::class
    ]
)
class FeatureModule