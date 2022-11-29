package com.edts.sampleapps.presentation.home

import androidx.lifecycle.ViewModel
import com.edts.sampleapps.data.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: EventRepository
) : ViewModel() {

    fun fetchEvents() {

    }

}