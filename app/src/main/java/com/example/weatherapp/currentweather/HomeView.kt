package com.example.weatherapp.currentweather

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.Settings.Constants.TempUnit
import com.example.weatherapp.Settings.Constants.TempUnitAPI
import com.example.weatherapp.Settings.Constants.TempUnitSymbol
import com.example.weatherapp.currentweather.components.FiveDayWeatherForecastCard
import com.example.weatherapp.currentweather.components.HourlyForecastCard
import com.example.weatherapp.currentweather.components.MainWeatherCard
import com.example.weatherapp.currentweather.components.WeatherItems
import com.example.weatherapp.data.models.Response.Response
import com.example.weatherapp.data.models.CurrentWeatherResponse
import com.example.weatherapp.data.models.ListOfWeather
import com.example.weatherapp.data.models.WeatherForecastResponse
import com.example.weatherapp.utils.Constants.SCREEN_PADDING
import com.example.weatherapp.utils.TemperatureUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter


@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeView(viewModel: HomeViewModel/*, latitude:Double, longitude:Double*/) {
    /*viewModel.readTempUnit()
    val savedTempUnit = viewModel.tempUnit.value
    when (savedTempUnit) {
        TempUnit.celsius -> {
            viewModel.getCurrentWeather(latitude, longitude, units = TempUnitAPI.celsius)
            viewModel.getWeatherForecast(latitude, longitude, units = TempUnitAPI.celsius)
        }
        TempUnit.kelvin -> {
            viewModel.getCurrentWeather(latitude, longitude, units = TempUnitAPI.kelvin)
            viewModel.getWeatherForecast(latitude, longitude, units = TempUnitAPI.kelvin)
        }
        TempUnit.fahrenheit -> {
            viewModel.getCurrentWeather(latitude, longitude, units = TempUnitAPI.fahrenheit)
            viewModel.getWeatherForecast(latitude, longitude, units = TempUnitAPI.fahrenheit)
        }
    }*/
    val latitude=viewModel.latitude.value
    val longitude=viewModel.longitude.value
    viewModel.getCurrentWeather(latitude, longitude)
    viewModel.getWeatherForecast(latitude, longitude)


    val currentWeatherState by viewModel.currentWeather.collectAsStateWithLifecycle()
    val weatherForecastState by viewModel.weatherForecast.collectAsStateWithLifecycle()


    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.message
            .filter { message -> message.isNotBlank() }
            .collect { message ->
                snackBarHostState.showSnackbar(
                    message = message, duration = SnackbarDuration.Short
                )
            }
    }




    if(currentWeatherState is Response.Loading || weatherForecastState is Response.Loading){
        LoadingIndicator()
    }else if(currentWeatherState is Response.Success<*> && weatherForecastState is Response.Success<*>){
        val currentWeatherData = (currentWeatherState as Response.Success<CurrentWeatherResponse>).data
        if (currentWeatherData != null) {

            val fiveDaysForecast = (weatherForecastState as Response.Success<WeatherForecastResponse>).data
            val hourlyForecast = fiveDaysForecast?.list
            HomeScreenContent(viewModel,currentWeatherData, hourlyForecast,fiveDaysForecast!!)


        }
    }else if(currentWeatherState is Response.Failure || weatherForecastState is Response.Failure){
        Text(
            text = "Sorry, we can't show the Weather now",
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(),
            fontSize = 22.sp


        )
    }



}

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HomeScreenContent(viewModel: HomeViewModel,currentWeather: CurrentWeatherResponse, hourlyForecast: List<ListOfWeather>?, fiveDaysForecast:WeatherForecastResponse) {
    var weatherIconIds: List<String> = emptyList()
    var hourlyTemps: List<Double> = emptyList()
    var hourlyTimes: List<String> = emptyList()

    if (hourlyForecast != null) {
        hourlyTemps = hourlyForecast.map { it.main.temp}
        hourlyTimes = hourlyForecast.map { it.dt_txt }

        for (i in hourlyForecast) {
            var iconId = hourlyForecast.map { it.weather[0].icon }
            weatherIconIds = iconId
        }
    } else {
        weatherIconIds = emptyList()
        hourlyTemps = emptyList()
        hourlyTimes = emptyList()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1D1E))
            .padding(SCREEN_PADDING)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            viewModel.readTempUnit()
           // viewModel.readOldTempUnit()

           // val oldTempUnit = viewModel.oldTempUnit.value
            val savedTempUnit = viewModel.tempUnit.value

            val currentTemp=currentWeather.main?.temp ?: 0.0
            val feelsLikeTemp = currentWeather.main?.feels_like ?: 0.0

            var convertedCurrentTemp = 0.0
            var convertedFeelsLikeTemp = 0.0

            var tempUnitSymbol = ""

            /*when(oldTempUnit){
                TempUnit.celsius ->
                    when(savedTempUnit){
                        TempUnit.celsius->{
                            tempUnitSymbol = TempUnitSymbol.celsius
                            convertedCurrentTemp = currentTemp
                            convertedFeelsLikeTemp = feelsLikeTemp
                        }
                        TempUnit.kelvin ->{
                            tempUnitSymbol = TempUnitSymbol.kelvin
                            convertedCurrentTemp = TemperatureUtils.celsiusToKelvin(currentTemp)
                            convertedFeelsLikeTemp = TemperatureUtils.celsiusToKelvin(feelsLikeTemp)
                        }

                        TempUnit.fahrenheit->{
                            tempUnitSymbol = TempUnitSymbol.fahrenheit
                            convertedCurrentTemp = TemperatureUtils.celsiusToFahrenheit(currentTemp)
                            convertedFeelsLikeTemp = TemperatureUtils.celsiusToFahrenheit(feelsLikeTemp)
                        }

                    }
                TempUnit.kelvin ->
                    when(savedTempUnit){
                        TempUnit.celsius->{
                            tempUnitSymbol = TempUnitSymbol.celsius
                            convertedCurrentTemp = TemperatureUtils.kelvinToCelsius(currentTemp)
                            convertedFeelsLikeTemp = TemperatureUtils.kelvinToCelsius(feelsLikeTemp)
                        }
                        TempUnit.kelvin ->{
                            tempUnitSymbol = TempUnitSymbol.kelvin
                            convertedCurrentTemp = currentTemp
                            convertedFeelsLikeTemp = feelsLikeTemp
                        }

                        TempUnit.fahrenheit->{
                            tempUnitSymbol = TempUnitSymbol.fahrenheit
                            convertedCurrentTemp = TemperatureUtils.kelvinToFahrenheit(currentTemp)
                            convertedFeelsLikeTemp = TemperatureUtils.kelvinToFahrenheit(feelsLikeTemp)
                        }

                    }
                TempUnit.fahrenheit ->
                    when(savedTempUnit){
                        TempUnit.celsius->{
                            tempUnitSymbol = TempUnitSymbol.celsius
                            convertedCurrentTemp = TemperatureUtils.fahrenheitToCelsius(currentTemp)
                            convertedFeelsLikeTemp = TemperatureUtils.fahrenheitToCelsius(feelsLikeTemp)
                        }
                        TempUnit.kelvin ->{
                            tempUnitSymbol = TempUnitSymbol.kelvin
                            convertedCurrentTemp = TemperatureUtils.fahrenheitToKelvin(currentTemp)
                            convertedFeelsLikeTemp = TemperatureUtils.fahrenheitToKelvin(feelsLikeTemp)
                        }

                        TempUnit.fahrenheit->{
                            tempUnitSymbol = TempUnitSymbol.fahrenheit
                            convertedCurrentTemp = currentTemp
                            convertedFeelsLikeTemp = feelsLikeTemp
                        }

                    }


            }
*/

            when(savedTempUnit) {
                TempUnit.celsius -> {
                    tempUnitSymbol = TempUnitSymbol.celsius
                    convertedCurrentTemp = currentTemp
                    convertedFeelsLikeTemp = feelsLikeTemp
                }

                TempUnit.kelvin -> {
                    tempUnitSymbol = TempUnitSymbol.kelvin
                    convertedCurrentTemp = TemperatureUtils.celsiusToKelvin(currentTemp)
                    convertedFeelsLikeTemp = TemperatureUtils.celsiusToKelvin(feelsLikeTemp)
                }
                TempUnit.fahrenheit -> {
                    tempUnitSymbol = TempUnitSymbol.fahrenheit
                    convertedCurrentTemp = TemperatureUtils.celsiusToFahrenheit(currentTemp)
                    convertedFeelsLikeTemp = TemperatureUtils.celsiusToFahrenheit(feelsLikeTemp)
                }
            }


            MainWeatherCard(
                weatherIcon = currentWeather.weather?.get(0)?.icon ?: "",
                weatherId = currentWeather.weather?.get(0)?.id ?: 0,
                dt = currentWeather.dt,
                timeZone = currentWeather.timezone,
                currentTemp = /*currentWeather.main?.temp?.toInt() ?: 0*/convertedCurrentTemp.toInt(),
                tempUnit = /*"C"*/tempUnitSymbol,
                feelsLikeTemp = /*currentWeather.main?.feels_like?.toInt() ?: 0*/convertedFeelsLikeTemp.toInt(),
                weatherDescription = currentWeather.weather?.get(0)?.main ?: "",
                location = currentWeather.name ?: ""
            )

            Spacer(modifier = Modifier.padding(12.dp))
            Box(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            )
            {

                Column(
                    modifier = Modifier
                        .fillMaxSize()

                ){
                    WeatherItems(currentWeather)
                    Spacer(modifier = Modifier.padding(12.dp))
                    HourlyForecastCard(
                        tempUnit = /*"C"*/tempUnitSymbol,
                        weatherIcons = weatherIconIds,
                        hourlyTemps = hourlyTemps,
                        hourlyTimes = hourlyTimes
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "5-Day Forecast",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    FiveDayWeatherForecastCard(fiveDaysForecast, /*"C"*/tempUnitSymbol)
                    Spacer(modifier = Modifier.height(100.dp))

                }

            }





        }
    }

}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ){
        CircularProgressIndicator()
    }

}

