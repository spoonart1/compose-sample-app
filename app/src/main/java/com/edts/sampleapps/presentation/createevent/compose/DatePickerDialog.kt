package com.edts.sampleapps.presentation.createevent.compose

import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.edts.sampleapps.ui.util.toDateAndMonth
import java.util.*


@Composable
fun DatePickerDialog(
    preSelectedDate: Long?,
    onCancelled: () -> Unit,
    onDatePicked: (date: String) -> Unit,
) {

    val calendar = Calendar.getInstance()
    if (preSelectedDate != null) {
        calendar.timeInMillis = preSelectedDate
    }

    val year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    val month by remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    val day by remember { mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }

    val context = LocalContext.current

    val datePicker = android.app.DatePickerDialog(
        context,
        { picker: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            calendar.set(mYear, mMonth, mDayOfMonth)
            val now = calendar.time
            onDatePicked.invoke(now.toDateAndMonth())
        },
        year,
        month,
        day
    )

    datePicker.setOnCancelListener {
        onCancelled.invoke()
    }

    datePicker.show()
}