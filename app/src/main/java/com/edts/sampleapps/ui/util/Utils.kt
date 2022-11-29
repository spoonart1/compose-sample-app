package com.edts.sampleapps.ui.util

import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.*

val months = arrayOf(
    "JAN", "FEB", "MAR", "APR",
    "MAY", "JUN", "JUL", "AUG",
    "SEP", "OCT", "NOV", "DEC"
)

fun Date.clockTime(): String {
    val formatter = SimpleDateFormat("hh.mm a", Locale.getDefault())
    return formatter.format(this)
}

fun Date.hour(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.HOUR)
}

fun Date.minute(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.MINUTE)
}

fun Date.toDefault(): String {
    val formatter = SimpleDateFormat("dd MMM - hh.mm a", Locale.getDefault())
    return formatter.format(this)
}

fun Date.toDateAndMonth(): String {
    val formatter = SimpleDateFormat("dd MMM", Locale.getDefault())
    return formatter.format(this)
}

fun Long.toDate(): Date {
    return Date(this)
}

fun String.toDateDefault(): Date {
    val formatter = SimpleDateFormat("dd MMM - hh.mm a", Locale.getDefault())
    return formatter.parse(this)!!
}

inline fun <reified Activity : ComponentActivity> Context.getActivity(): Activity? {
    return when (this) {
        is Activity -> this
        else -> {
            var context = this
            while (context is ContextWrapper) {
                context = context.baseContext
                if (context is Activity) return context
            }
            null
        }
    }
}

fun showToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}