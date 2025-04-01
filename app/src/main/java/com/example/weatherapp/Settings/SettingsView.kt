package com.example.weatherapp.Settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun SettingsView(viewModel: SettingsViewModel) {
    Text(
    text = "Settings Screen",
    modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(),
    fontSize = 22.sp


    )
}