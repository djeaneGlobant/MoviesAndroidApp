package com.example.eventlist.di

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        EventListContributeModule::class
    ]
)
class EventListModule