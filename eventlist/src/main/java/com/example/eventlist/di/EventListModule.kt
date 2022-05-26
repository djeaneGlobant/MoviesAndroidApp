package com.example.eventlist.di

import dagger.Module

@Module(
    includes = [
        NetworkModule::class,
        EventDbModule::class,
        EventListContributeModule::class
    ]
)
class EventListModule