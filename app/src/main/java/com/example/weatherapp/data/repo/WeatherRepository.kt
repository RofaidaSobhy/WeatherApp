package com.example.weatherapp.data.repo

import com.example.weatherapp.Constants.API_KEY
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.data.remote.CurrentWeatherResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude:Double, longitude:Double, apiKey: String, units: String, language: String): Flow<CurrentWeatherResponse?>

}
