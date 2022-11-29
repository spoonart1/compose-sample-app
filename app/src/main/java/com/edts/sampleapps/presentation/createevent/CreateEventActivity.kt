@file:OptIn(ExperimentalMaterial3Api::class)

package com.edts.sampleapps.presentation.createevent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import com.edts.sampleapps.presentation.createevent.compose.Body
import com.edts.sampleapps.presentation.createevent.compose.TopBarWidget
import com.edts.sampleapps.ui.theme.SampleAppsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateEventActivity : ComponentActivity() {

    private val viewModel: CreateEventViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SampleAppsTheme {
                Scaffold(topBar = {
                    TopBarWidget(onNavigationClick = {
                        this.finish()
                    })
                }) {
                    Body(viewModel, it)
                }
            }
        }

        viewModel.eventData.observe(this) {

        }
    }
}