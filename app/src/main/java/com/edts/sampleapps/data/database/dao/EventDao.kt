package com.edts.sampleapps.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.edts.sampleapps.data.database.entity.EventEntity

@Dao
interface EventDao {

    @Insert
    suspend fun insert(entity: EventEntity) : Long

    @Query("SELECT * from event")
    suspend fun getAll(): List<EventEntity>

}