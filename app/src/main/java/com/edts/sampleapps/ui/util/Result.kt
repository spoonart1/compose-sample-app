package com.edts.sampleapps.ui.util


sealed class EventResult<out T> {
    object Loading : EventResult<Nothing>()
    data class Error(val throwable: Throwable) : EventResult<Nothing>()
    data class Success<T>(val data: T) : EventResult<T>()
}