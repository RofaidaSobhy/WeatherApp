package com.example.weatherapp.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.weatherapp.R
import com.example.weatherapp.settings.Constants.WindSpeedUnit
import com.example.weatherapp.settings.Constants.WindSpeedUnitSymbol
import com.example.weatherapp.home.HomeViewModel
import com.example.weatherapp.data.models.CurrentWeatherResponse
import com.example.weatherapp.utils.WindSpeedUtils

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherItemCard(
    icon: Int,
    title: String,
    value: String
) {
    val color1 = Color(0xFF4776E6)
    val color2 = Color(0xFF8E54E9)

    val gradient = Brush.verticalGradient(
        colors = listOf(color1, color2)
    )

    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier.widthIn(max = 130.dp).heightIn(max = 140.dp)
    ) {
        Box(
            modifier = Modifier
                .background(brush = gradient)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    modifier = Modifier.size(25.dp),
                    tint = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = value,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherItemCardPreview() {
    WeatherItemCard(
        icon = R.drawable.wind,
        title = "Wind",
        value = "5 km/h"
    )
}

@Composable
fun WeatherItems(viewModel: HomeViewModel, currentWeather: CurrentWeatherResponse) {
    val spacing = 3.dp

    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(spacing)
    ) {

        viewModel.readWindSpeedUnit()
        val savedWindSpeedUnit = viewModel.windSpeedUnit.value

        val windSpeed=currentWeather.wind?.speed ?: 0.0

        var convertedWindSpeed = 0.0
        var windSpeedUnitSymbol = ""
        when(savedWindSpeedUnit) {
            WindSpeedUnit.meter -> {
                windSpeedUnitSymbol = WindSpeedUnitSymbol.meter
                convertedWindSpeed = windSpeed
            }

            WindSpeedUnit.mile -> {
                windSpeedUnitSymbol = WindSpeedUnitSymbol.mile
                convertedWindSpeed = WindSpeedUtils.mpsToMph(windSpeed)
            }

        }
        items(4) { index ->
            WeatherItemCard(
                icon = when(index) {
                    0 -> R.drawable.wind
                    1 -> R.drawable.pressure
                    2 -> R.drawable.humidity
                    else -> R.drawable.cloud
                },
                title = when(index) {
                    0 -> "Wind"
                    1 -> "Pressure"
                    2 -> "Humidity"
                    else -> "Cloud"
                },
                value = "${convertedWindSpeed.toInt()}".let {
                    when(index) {
                        0 -> "$it $windSpeedUnitSymbol"
                        1 -> "${currentWeather.main?.pressure} MB"
                        2 -> "${currentWeather.main?.humidity}%"
                        else -> "${currentWeather.clouds?.all?.times(100)}%"
                    }
                }
            )
            Spacer(modifier = Modifier.width(spacing))
        }
    }
}