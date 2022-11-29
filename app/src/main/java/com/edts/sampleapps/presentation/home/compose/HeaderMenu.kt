@file:OptIn(ExperimentalUnitApi::class)

package com.edts.sampleapps.presentation.home.compose

import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.edts.sampleapps.presentation.home.menuItems
import com.edts.sampleapps.presentation.home.model.GridItem
import com.edts.sampleapps.ui.theme.Orange

@Composable
internal fun HeaderMenu(windowManager: WindowManager, content: @Composable () -> Unit) {
    val menuSize = 240
    val metrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(metrics)
    val grid = metrics.widthPixels / menuSize
    val paddingStartEnd = 16.dp
    Column(
        modifier = Modifier
            .padding(
                start = paddingStartEnd,
                top = 8.dp,
                end = paddingStartEnd
            )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MenuWidget { menuItem ->
                    println("menu: ${menuItem.description}")
                }
            }
        }
        content()
    }
}

@Composable
private fun MenuWidget(onClick: (GridItem) -> Unit) {
    val topMenuCount = menuItems.size / 2 + 1
    val secondMenuCount = menuItems.size - topMenuCount
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in 0 until topMenuCount) {
            Item(item = menuItems[i], onClick = { onClick.invoke(menuItems[i]) })
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (i in secondMenuCount until menuItems.size) {
            Item(item = menuItems[i], onClick = { onClick.invoke(menuItems[i]) })
        }
    }
}

@Composable
private fun Item(
    item: GridItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(10f))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = onClick
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(item.icon, item.description, tint = Orange)
        Text(
            modifier = Modifier.padding(2.dp),
            textAlign = TextAlign.Center,
            text = item.description,
            style = TextStyle(fontSize = TextUnit(9f, TextUnitType.Sp))
        )
    }
}
