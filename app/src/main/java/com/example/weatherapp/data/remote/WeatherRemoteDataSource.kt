package com.example.weatherapp.data.remote

import com.example.weatherapp.data.models.CurrentWeatherResponse
import com.example.weatherapp.data.models.WeatherForecastResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRemoteDataSource{
    suspend fun getCurrentWeather(latitude:Double, longitude:Double, apiKey: String, units: String, language: String): Flow<CurrentWeatherResponse?>
    suspend fun getWeatherForecast(latitude:Double, longitude:Double, apiKey: String, units: String, language: String): Flow<WeatherForecastResponse?>

}