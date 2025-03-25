package com.example.weatherapp.data.remote

import com.example.weatherapp.Constants.API_KEY
import com.example.weatherapp.data.models.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRemoteDataSource{
    suspend fun getCurrentWeather(latitude:Double, longitude:Double, apiKey: String, units: String, language: String): Flow<CurrentWeatherResponse?>
}