package com.example.localstorage.di

import android.content.Context
import androidx.room.Room
import com.example.localstorage.database.EventDataBase
import dagger.Module
import dagger.Provides

@Module
class LocalStorageModule {

    @Provides
    fun provideEventDataBase(context: Context): EventDataBase = Room.databaseBuilder(
        context,
        EventDataBase::class.java,
        "event_db"
    ).build()
}