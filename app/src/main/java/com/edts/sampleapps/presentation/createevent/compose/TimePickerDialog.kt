package com.edts.sampleapps.presentation.createevent.compose

import android.app.TimePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.edts.sampleapps.ui.util.clockTime
import java.util.*

@Composable
fun TimePicker(
    initHour: Int,
    initMinute: Int,
    onCancelled: () -> Unit,
    onTimePicked: (String) -> Unit
) {
    val calendar = Calendar.getInstance()
    val context = LocalContext.current
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            calendar.set(Calendar.HOUR, hour)
            calendar.set(Calendar.MINUTE, minute)
            val now = calendar.time
            onTimePicked(now.clockTime())
        }, initHour, initMinute, false
    )
    timePickerDialog.show()
    timePickerDialog.setOnCancelListener { onCancelled() }
}