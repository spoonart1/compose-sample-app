@file:OptIn(ExperimentalMaterial3Api::class)

package com.edts.sampleapps.presentation.createevent.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.edts.sampleapps.ui.theme.TextSizeTopBar
import com.edts.sampleapps.ui.theme.TextStyleBold

@Composable
internal fun TopBarWidget(onNavigationClick: () -> Unit) {
    TopAppBar(title = {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Buat Event",
                style = TextStyleBold.copy(fontSize = TextSizeTopBar)
            )
        }
    }, navigationIcon = {
        IconButton(onClick = onNavigationClick) {
            Icon(
                Icons.Filled.ChevronLeft, contentDescription = "back"
            )
        }
    }, colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = Color.White
    ), scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}