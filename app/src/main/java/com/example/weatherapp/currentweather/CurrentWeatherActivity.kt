package com.example.weatherapp.currentweather

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.currentweather.ui.theme.WeatherAppTheme
import com.example.weatherapp.data.models.Response.Response
import com.example.weatherapp.data.remote.CurrentWeatherResponse
import com.example.weatherapp.data.remote.RetrofitHelper
import com.example.weatherapp.data.remote.WeatherRemoteDataSourceImpl
import com.example.weatherapp.data.repo.WeatherRepositoryImpl
import kotlinx.coroutines.flow.filter

class CurrentWeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val latitude:Double = 26.820553
        val longitude:Double = 30.802498
        setContent {
            CurrentWeatherScreen(
                ViewModelProvider(this, CurrentWeatherFactory(
                   WeatherRepositoryImpl.getInstance(
                        WeatherRemoteDataSourceImpl(RetrofitHelper.apiService)

                    )
                )
                ).get(CurrentWeatherViewModel::class.java),
                latitude,
                longitude
            )
        }
    }
}

@Composable
private fun CurrentWeatherScreen(viewModel: CurrentWeatherViewModel, latitude:Double, longitude:Double) {
    viewModel.getCurrentWeather(latitude, longitude)

    val currentWeatherState by viewModel.currentWeather.collectAsStateWithLifecycle()
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

    //(currentWeatherState as Response.Success<Root>).data?.weather?.get(0)?.main


    when (currentWeatherState) {
        is Response.Loading -> {
            Log.i("TAG", "CurrentWeatherScreen: Success ")
            LoadingIndicator()
        }

        is Response.Success<*> -> {
            Text(
                text = "weather(main): ${(currentWeatherState as Response.Success<CurrentWeatherResponse>).data}",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(),
                fontSize = 22.sp


            )
            Log.i("TAG", "CurrentWeatherScreen: Success ")
        }
        is Response.Failure -> {
            Text(
                text = "Sorry, we can't show the CurrentWeather now",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(),
                fontSize = 22.sp


            )
            Log.i("TAG", "CurrentWeatherScreen: Failure ")
        }
    }

}


@Composable
private fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ){
        CircularProgressIndicator()
    }

}