package com.edts.sampleapps.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "location")
    val location: String,

    //pattern 'dd MMM - hh.mm a'
    //saved in millis
    @ColumnInfo(name = "start_date")
    val startDate: Long,

    //pattern 'dd MMM - hh.mm a'
    //saved in millis
    @ColumnInfo(name = "end_date")
    val endDate: Long,

    @ColumnInfo(name = "image_url")
    val imageUrl:String,

    @ColumnInfo(name = "description")
    val description:String,

    @ColumnInfo(name = "holder")
    val holder:String
)