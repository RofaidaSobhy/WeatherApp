package com.example.weatherapp.Settings


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.Settings.components.LanguageCard
import com.example.weatherapp.Settings.components.LocationCard
import com.example.weatherapp.Settings.components.TempUnitCard
import com.example.weatherapp.Settings.components.WindSpeedUnitCard
import com.example.weatherapp.utils.Constants.SCREEN_PADDING

@Composable
fun SettingsView(viewModel: SettingsViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1D1E))
            .padding(SCREEN_PADDING)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            LanguageCard()
            Spacer(modifier = Modifier.height(10.dp))
            TempUnitCard()
            Spacer(modifier = Modifier.height(10.dp))
            LocationCard()
            Spacer(modifier = Modifier.height(10.dp))
            WindSpeedUnitCard()

        }
    }
}













