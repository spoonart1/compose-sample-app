@file:OptIn(ExperimentalUnitApi::class)

package com.edts.sampleapps.presentation.home.compose

import android.graphics.Bitmap
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.edts.sampleapps.presentation.home.model.Event
import com.edts.sampleapps.ui.theme.*
import com.edts.sampleapps.ui.util.eventMocks
import com.edts.sampleapps.ui.util.toDefault
import java.util.*

@Composable
internal fun Body(paddingValues: PaddingValues, windowManager: WindowManager) {
    Surface(
        modifier = Modifier.padding(paddingValues)
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
        )

        Box(
            modifier = Modifier
                .background(OrangeTopBar)
                .fillMaxWidth()
                .size(60.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(unbounded = false)
                .verticalScroll(rememberScrollState())
        ) {
            HeaderMenu(windowManager = windowManager) {
                Spacer(modifier = Modifier.height(12.dp))
                UpcomingEvent(
                    Event(
                        "https://th.bing.com/th/id/OIP.m9aLWmwmd8peibRDI9Im0gHaEo?pid=ImgDet&rs=1",
                        "JUL",
                        "13",
                        "Android Training Day",
                        "Conference Room",
                        "Elevenia F30",
                        Date()
                    )
                )
                NewEvent()
            }
        }
    }
}

@Composable
private fun ImageFeatured(imageUrl: String) {
    AsyncImage(
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(6)),
        model = ImageRequest.Builder(LocalContext.current)
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun UpcomingEvent(event: Event) {
    ImageFeatured(event.image)
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = event.month,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Orange
                )
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = event.date, style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = TextUnit(
                        20f, TextUnitType.Sp
                    )
                )
            )
        }
        Spacer(Modifier.width(16.dp))
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = event.eventName,
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${event.eventDesc} - ${event.holder}", style = TextStyle(
                    color = Color.LightGray,
                    fontSize = TextUnit(
                        12f, TextUnitType.Sp
                    )
                )
            )
        }
    }
}

@Composable
private fun NewEvent() {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Event Terbaru", style = TextStyleBold.copy(
                fontSize =
                TextUnit(16f, TextUnitType.Sp)
            )
        )
        ClickableText(text = AnnotatedString("Lihat Semua"),
            style = TextStyleOrange,
            onClick = {
                println("lihat semua")
            })
    }
    Spacer(modifier = Modifier.height(10.dp))
    repeat(eventMocks.size) {
        EventItem(event = eventMocks[it])
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun EventItem(event: Event) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(6)),
            model = ImageRequest.Builder(LocalContext.current)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .data(event.image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier, horizontalAlignment = Alignment.Start) {
            Text(
                text = event.eventName, style =
                TextStyleBold.copy(fontSize = TextSize14)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = event.eventDate.toDefault(), style = TextStyle(color = Color.LightGray))

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = event.eventName, style = TextStyle(color = Color.LightGray))
        }
    }
}