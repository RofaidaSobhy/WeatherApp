package com.example.weatherapp.currentweather.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.weatherapp.utils.DateTimeUtils
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyForecastCard(
    tempUnit: String,
    weatherIcons: List<String>,
    hourlyTemps: List<Int>,
    hourlyTimes: List<String>
) {
    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF222A36))
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            val spacing = 15.dp

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(spacing),
                modifier = Modifier.padding(horizontal = 16.dp)
            ){
                items(weatherIcons.size) { index ->
                    HourlyForecastItem(
                        weatherIcon = weatherIcons[index],
                        tempUnit = tempUnit,
                        hourlyTemp = hourlyTemps[index],
                        hourlyTime = hourlyTimes[index]
                    )
                }
            }

        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HourlyForecastItem(
    weatherIcon: String,
    tempUnit:String,
    hourlyTemp: Int,
    hourlyTime: String,
) {

    val timeStamp = DateTimeUtils.parseDtTxtToHour(hourlyTime)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = timeStamp.uppercase(Locale.ROOT),
            style = MaterialTheme.typography.titleSmall,
            color = Color(0xFF9396A6)

        )

        GlideImage(
            model = "https://openweathermap.org/img/wn/${weatherIcon}@2x.png",
            contentDescription = "Weather icon",
            modifier = Modifier
                .size(48.dp)

        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$hourlyTemp°$tempUnit",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onPrimary,
        )


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun HourlyForecastCardPreview() {
        HourlyForecastCard(
            tempUnit = "C",
            weatherIcons = listOf(
                "", "", "", "", ""
            ),
            hourlyTemps = listOf(20, 20, 20, 20, 20),
            hourlyTimes = listOf("NOW", "13:00", "14:00", "15:00", "16:00")
        )

}