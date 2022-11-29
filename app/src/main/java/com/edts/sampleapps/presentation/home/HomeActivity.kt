@file:OptIn(
    ExperimentalMaterial3Api::class
)

package com.edts.sampleapps.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import com.edts.sampleapps.presentation.home.compose.Body
import com.edts.sampleapps.presentation.home.compose.TopBarWidget
import com.edts.sampleapps.ui.theme.SampleAppsTheme

class HomeActivity : ComponentActivity() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppsTheme {
                Scaffold(
                    topBar = {
                        TopBarWidget()
                    }, content = {
                        Body(it, windowManager)
                    })
            }
        }
    }
}

