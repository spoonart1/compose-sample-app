package com.edts.sampleapps.di

import com.edts.sampleapps.data.database.dao.EventDao
import com.edts.sampleapps.data.repository.EventRepository
import com.edts.sampleapps.data.repository.EventRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object CommonModule {

    @Provides
    @Reusable
    fun provideRepository(dao: EventDao): EventRepository {
        return EventRepositoryImpl(dao)
    }

}