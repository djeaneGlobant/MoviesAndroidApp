package com.example.eventlist.di

import android.content.Context
import androidx.room.Room
import com.example.eventlist.data.database.EventDataBase
import dagger.Module
import dagger.Provides

@Module
class EventDbModule {

    @Provides
    fun provideEventDataBase(context: Context): EventDataBase = Room.databaseBuilder(
        context,
        EventDataBase::class.java,
        "event_db"
    ).build()
}