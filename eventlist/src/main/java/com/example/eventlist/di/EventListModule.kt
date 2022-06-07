package com.example.eventlist.di

import dagger.Module

@Module(
    includes = [
        EventListContributeModule::class
    ]
)
class EventListModule