package com.example.weatherapp.data.remote

import kotlinx.coroutines.flow.Flow

interface WeatherRemoteDataSource{
    suspend fun getCurrentWeather(latitude:Double, longitude:Double, apiKey: String, units: String, language: String): Flow<CurrentWeatherResponse?>
}