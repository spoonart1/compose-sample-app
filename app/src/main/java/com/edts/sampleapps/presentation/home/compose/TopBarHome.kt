@file:OptIn(ExperimentalMaterial3Api::class)

package com.edts.sampleapps.presentation.home.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.edts.sampleapps.R
import com.edts.sampleapps.ui.theme.OrangeTopBar
import com.edts.sampleapps.ui.theme.TopBarTextStyleHeaderBold
import com.edts.sampleapps.ui.theme.TopBarTextStyleHeaderNormal

@Composable
internal fun TopBarWidget() {
    TopAppBar(
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Halo Selamat pagi,",
                    color = Color.White,
                    style = TopBarTextStyleHeaderNormal
                )
                Text(
                    text = "John Doe Simalakama",
                    color = Color.White,
                    style = TopBarTextStyleHeaderBold
                )
            }
        },
        navigationIcon = {
            ImageAvatar {
                println("click image")
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.Notifications,
                    contentDescription = "notificaiton",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = OrangeTopBar
        ),
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}

@Composable
internal fun ImageAvatar(onClick: () -> Unit) {
    IconButton(modifier = Modifier, onClick = onClick) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            // crop the image if it's not a square
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
        )
    }
}