package com.edts.sampleapps.mapper

import com.edts.sampleapps.data.database.entity.EventEntity
import com.edts.sampleapps.presentation.createevent.model.CreateEventModel
import com.edts.sampleapps.presentation.home.model.Event
import com.edts.sampleapps.ui.util.months
import com.edts.sampleapps.ui.util.toDate
import com.edts.sampleapps.ui.util.toDateDefault
import java.util.*

class EventEntityMapper {

    fun toEntity(source: CreateEventModel): EventEntity {
        return EventEntity(
            name = source.name,
            location = source.location,
            startDate = source.startDate.toDateDefault().time,
            endDate = source.endDate.toDateDefault().time,
            imageUrl = source.picturePath,
            description = source.description,
            holder = source.holder
        )
    }

    fun toEventModel(entity: EventEntity): Event {
        val startDate = entity.startDate.toDate()
        val calendar = Calendar.getInstance()
        calendar.time = startDate

        val month = months[calendar.get(Calendar.MONTH)]
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return Event(
            image = entity.imageUrl,
            month = month,
            date = day.toString(),
            eventName = entity.name,
            eventDesc = entity.description,
            holder = entity.holder,
            eventDate = startDate
        )
    }

}