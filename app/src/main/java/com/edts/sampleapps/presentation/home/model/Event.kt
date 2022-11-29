package com.edts.sampleapps.presentation.home.model

import java.util.*

data class Event(
    val image: String,
    val month: String,
    val date: String,
    val eventName: String,
    val eventDesc: String,
    val holder: String,
    val eventDate: Date = Date()
)