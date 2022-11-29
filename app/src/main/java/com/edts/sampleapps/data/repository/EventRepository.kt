package com.edts.sampleapps.data.repository

import com.edts.sampleapps.data.database.dao.EventDao
import com.edts.sampleapps.data.database.entity.EventEntity
import javax.inject.Inject


interface EventRepository {
    suspend fun saveData(entity: EventEntity): Long

    suspend fun getAll(): List<EventEntity>
}

class EventRepositoryImpl @Inject constructor(
    private val eventDao: EventDao
) : EventRepository {
    override suspend fun saveData(entity: EventEntity): Long {
        return eventDao.insert(entity)
    }

    override suspend fun getAll(): List<EventEntity> {
        return eventDao.getAll()
    }
}