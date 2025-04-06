package com.example.weatherapp.settings


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
import com.example.weatherapp.settings.components.LanguageCard
import com.example.weatherapp.settings.components.LocationCard
import com.example.weatherapp.settings.components.TempUnitCard
import com.example.weatherapp.settings.components.WindSpeedUnitCard
import com.example.weatherapp.utils.Constants.SCREEN_PADDING

@Composable
fun SettingsView(viewModel: SettingsViewModel, action : ()-> Unit) {
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
            LanguageCard(viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            TempUnitCard(viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            LocationCard(action, viewModel)
            Spacer(modifier = Modifier.height(10.dp))
            WindSpeedUnitCard(viewModel)

        }
    }
}













