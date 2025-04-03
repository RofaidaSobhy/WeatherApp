package com.example.weatherapp.currentweather.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.weatherapp.Settings.Constants.TempUnit
import com.example.weatherapp.Settings.Constants.TempUnitSymbol
import com.example.weatherapp.data.models.ListOfWeather
import com.example.weatherapp.data.models.WeatherForecastResponse
import com.example.weatherapp.utils.DateTimeUtils
import com.example.weatherapp.utils.TemperatureUtils


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FiveDayWeatherForecastCard(
    weatherForecast: WeatherForecastResponse,
    tempUnit: String
) {
    val weatherForecastItems: List<ListOfWeather> = weatherForecast.list

    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 350.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF222A36))
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Column {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(5) { index ->
                        WeatherForecastItem(weatherForecastItems[index*8],tempUnit)
                        if (index in 1 until weatherForecastItems.size) {
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherForecastItem(
    weatherItem: ListOfWeather,
    tempUnit: String
) {

    val tempMin = weatherItem.main.temp_min
    val tempMax = weatherItem.main.temp_max

    var convertedTempMin = 0.0
    var convertedTempMax = 0.0

    when(tempUnit) {
        TempUnitSymbol.celsius -> {
            convertedTempMin = tempMin
            convertedTempMax = tempMax
        }

        TempUnitSymbol.kelvin -> {
            convertedTempMin = TemperatureUtils.celsiusToKelvin(tempMin)
            convertedTempMax = TemperatureUtils.celsiusToKelvin(tempMax)
        }
        TempUnitSymbol.fahrenheit -> {

            convertedTempMin = TemperatureUtils.celsiusToFahrenheit(tempMin)
            convertedTempMax = TemperatureUtils.celsiusToFahrenheit(tempMax)
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        val dayMonth: String = DateTimeUtils.parseDtTxtToDayMonth(weatherItem.dt_txt)
        val weatherIcon= weatherItem.weather[0].icon

        Text(
            text = dayMonth,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF9396A6),
        )

        GlideImage(
            model = "https://openweathermap.org/img/wn/${weatherIcon}@2x.png",
            contentDescription = "Weather icon",
            modifier = Modifier
                .size(44.dp)

        )

        Text(
            text = "${/*weatherItem.main.temp_min*/convertedTempMin.toInt()}°$tempUnit",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = "${/*weatherItem.main.temp_max*/convertedTempMax.toInt()}°$tempUnit",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF9396A6),
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = weatherItem.weather[0].main.uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF9396A6),
            maxLines = 2,
            textAlign = TextAlign.End,
        )

    }
}