@file:OptIn(ExperimentalMaterial3Api::class)

package com.edts.sampleapps.presentation.createevent.compose

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PictureInPictureAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.edts.sampleapps.presentation.createevent.CreateEventViewModel
import com.edts.sampleapps.ui.theme.*
import com.edts.sampleapps.ui.util.hour
import com.edts.sampleapps.ui.util.minute
import java.util.*

@Composable
internal fun Body(viewModel: CreateEventViewModel, paddingValues: PaddingValues) {
    Surface(
        modifier = Modifier.padding(paddingValues)
    ) {
        Box(modifier = Modifier.background(Color.White)) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                Description(viewModel)
                Spacer(modifier = Modifier.height(18.dp))
                DateOfEvent(viewModel)

                Spacer(modifier = Modifier.height(32.dp))
                AddImageEvent(viewModel)
            }
        }
    }
}

@Composable
private fun Description(viewModel: CreateEventViewModel) {
    var namaEvent by remember { mutableStateOf(TextFieldValue()) }
    var lokasiEvent by remember { mutableStateOf(TextFieldValue()) }
    val isNameEventError by remember { mutableStateOf(false) }
    val isLokasiEventError by remember { mutableStateOf(false) }

    TextFieldEvent(
        modifier = Modifier.fillMaxWidth(),
        value = namaEvent,
        onValueChange = {
            namaEvent = it
        }, label = {
            Text(text = "Nama Event")
        }, isError = isNameEventError
    )

    Spacer(modifier = Modifier.height(4.dp))
    TextFieldEvent(modifier = Modifier.fillMaxWidth(),
        value = lokasiEvent,
        onValueChange = {
            lokasiEvent = it
        }, label = {
            Text(text = "Lokasi Event")
        }, isError = isLokasiEventError
    )

    viewModel.bindEventNameAndLocation(namaEvent.text, lokasiEvent.text)
}

@Composable
private fun DateOfEvent(viewModel: CreateEventViewModel) {
    var showDatePickerDari by remember { mutableStateOf(false) }
    var showDatePickerHingga by remember { mutableStateOf(false) }

    var showTimePickerDari by remember { mutableStateOf(false) }
    var showTimePickerHingga by remember { mutableStateOf(false) }

    var tanggalDari by remember { mutableStateOf("Tanggal Dari") }
    var tanggalHingga by remember { mutableStateOf("Tanggal Hingga") }

    var pukulDari by remember { mutableStateOf("Pukul") }
    var pukulHingga by remember { mutableStateOf("Pukul") }

    Text(text = "TANGGAL EVENT", style = TextStyleSemiBold.copy(fontSize = TextSize10))
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable {
                    showDatePickerDari = true
                }, text = tanggalDari, style = TextStyle(color = Color.LightGray)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable {
                    showTimePickerDari = true
                }, text = pukulDari, style = TextStyle(color = Color.LightGray)
        )
    }
    Spacer(modifier = Modifier.height(18.dp))
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable {
                    showDatePickerHingga = true
                }, text = tanggalHingga, style = TextStyle(color = Color.LightGray)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable {
                    showTimePickerHingga = true
                }, text = pukulHingga, style = TextStyle(color = Color.LightGray)
        )
    }

    if (showDatePickerDari) {
        DatePickerDialog(null, onCancelled = {
            showDatePickerDari = false
        }, onDatePicked = { dateStr ->
            tanggalDari = dateStr
            showDatePickerDari = false
        })
    }

    if (showDatePickerHingga) {
        DatePickerDialog(null, onCancelled = {
            showDatePickerHingga = false
        }, onDatePicked = { dateStr ->
            tanggalHingga = dateStr
            showDatePickerHingga = false
        })
    }

    if (showTimePickerDari) {
        val now = Date()
        TimePicker(initHour = now.hour(), initMinute = now.minute(), onCancelled = {
            showTimePickerDari = false
        }) { timeStr ->
            pukulDari = timeStr
            showTimePickerDari = false
        }
    }

    if (showTimePickerHingga) {
        val now = Date()
        TimePicker(initHour = now.hour(), initMinute = now.minute(), onCancelled = {
            showTimePickerHingga = false
        }) { timeStr ->
            pukulHingga = timeStr
            showTimePickerHingga = false
        }
    }

    viewModel.bindEventDateTime(tanggalDari, tanggalHingga, pukulDari, pukulHingga)
}

@Composable
private fun AddImageEvent(viewModel: CreateEventViewModel) {
    val charLimit = 300
    var deskripsiEvent by remember { mutableStateOf(TextFieldValue()) }
    var holderEvent by remember { mutableStateOf(TextFieldValue()) }
    val imageData = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
            imageData.value = it
        })

    Text(text = "TAMBAHKAN GAMBAR EVENT", style = TextStyleSemiBold.copy(fontSize = TextSize10))
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Color.White)
                .border(1.dp, Color.LightGray, RoundedCornerShape(6))
                .clickable {
                    launcher.launch("image/*")
                },
            contentAlignment = Alignment.Center
        ) {
            if (imageData.value == null) {
                Icon(Icons.Filled.PictureInPictureAlt, contentDescription = "add image")
            } else {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = imageData.value.toString(),
                    contentDescription = "image of event",
                    contentScale = ContentScale.Crop
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Pilih gambar yang sesuai dengan event Anda. Kami merekomendasikan menggunakan 2160x1080px dan gambar tidak lebih besar dari 10MB",
            style = TextStyle(fontSize = TextSize12, color = Color.Gray)
        )
    }
    Spacer(modifier = Modifier.height(32.dp))
    Text(text = "DESKRPSI EVENT", style = TextStyleSemiBold.copy(fontSize = TextSize10))
    Spacer(modifier = Modifier.height(4.dp))
    TextFieldEvent(
        modifier = Modifier.fillMaxWidth(),
        value = deskripsiEvent,
        onValueChange = {
            deskripsiEvent = it
        }, trailingIcon = {
            Text(text = "${deskripsiEvent.text.length}/$charLimit")
        }, isError = deskripsiEvent.text.length > charLimit
    )
    Spacer(modifier = Modifier.height(32.dp))
    Text(text = "PENYELENGGARA ACARA", style = TextStyleSemiBold.copy(fontSize = TextSize10))
    Spacer(modifier = Modifier.height(4.dp))
    TextFieldEvent(
        modifier = Modifier.fillMaxWidth(),
        value = holderEvent,
        onValueChange = {
            holderEvent = it
        }
    )
    Spacer(modifier = Modifier.height(32.dp))
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10), colors = ButtonDefaults.buttonColors(
            containerColor = DarkBlue,
            contentColor = White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.White
        ),
        onClick = {
            viewModel.bindEventDescription(deskripsiEvent.text, holderEvent.text)
            viewModel.bindEventImage(imageData.value.toString())
            viewModel.saveData()
        }) {
        Text(text = "SELANJUTNYA")
    }
}

@Composable
fun TextFieldEvent(
    modifier: Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange, label = label,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedLabelColor = GreenLeafe,
            unfocusedLabelColor = Color.LightGray,
            focusedIndicatorColor = GreenLeafe,
            unfocusedIndicatorColor = Color.LightGray
        ),
        isError = isError,
        trailingIcon = trailingIcon
    )
}