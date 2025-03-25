package com.example.weatherapp.data.remote

import com.example.weatherapp.data.models.Weather
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WeatherRemoteDataSourceImpl(private val service: WeatherService) :WeatherRemoteDataSource {
    override suspend fun getCurrentWeather(latitude:Double, longitude:Double, apiKey: String, units: String, language: String): Flow<CurrentWeatherResponse?> {
        return flowOf( service.getCurrentWeather(latitude,longitude,apiKey,units,language).body())
    }
}