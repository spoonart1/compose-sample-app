package com.edts.sampleapps.di

import android.content.Context
import com.edts.sampleapps.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        AppDatabase.getInstance(context)


    @Provides
    @Singleton
    fun provideEventDao(database: AppDatabase) = database.eventDao()

}