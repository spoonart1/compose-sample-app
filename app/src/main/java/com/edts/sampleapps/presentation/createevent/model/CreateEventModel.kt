package com.edts.sampleapps.presentation.createevent.model

data class CreateEventModel(
    var name: String = "",
    var location: String = "",
    var startDate: String = "",
    var endDate: String = "",
    var picturePath: String = "",
    var description: String = "",
    var holder: String = ""
)