package com.edts.sampleapps.presentation.createevent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edts.sampleapps.data.repository.EventRepository
import com.edts.sampleapps.presentation.createevent.model.CreateEventModel
import com.edts.sampleapps.ui.util.EventResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {

    private val eventModel = CreateEventModel()
    private val _event = MutableLiveData<EventResult<CreateEventModel>>()

    val eventData: LiveData<EventResult<CreateEventModel>>
        get() = _event

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _event.value = EventResult.Error(exception)
    }

    fun bindEventNameAndLocation(name: String, location: String) {
        eventModel.name = name
        eventModel.location = location
    }

    fun bindEventDateTime(startDate: String, endDate: String, startTime: String, endTime: String) {
        eventModel.startDate = startDate
        eventModel.endDate = endDate
    }

    fun bindEventImage(imagePath: String) {
        eventModel.picturePath = imagePath
    }

    fun bindEventDescription(description: String, holder: String) {
        eventModel.description = description
        eventModel.holder = holder
    }

    fun saveData() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {


            _event.value = EventResult.Success(eventModel)
        }
    }
}