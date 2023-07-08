package com.example.runningapplication.di

import android.content.Context
import androidx.room.Room
import com.example.runningapplication.db.RunDao
import com.example.runningapplication.db.RunDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EntryModule {
    @Singleton
    @Provides
    fun provideRunDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RunDataBase::class.java, "run_db").build()


    @Singleton
    @Provides
    fun provideRunDao(db: RunDataBase) : RunDao = db.getRunDao()
}