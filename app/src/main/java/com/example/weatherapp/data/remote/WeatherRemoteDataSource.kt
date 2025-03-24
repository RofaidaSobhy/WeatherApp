package com.example.weatherapp.data.remote

import com.example.weatherapp.data.models.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRemoteDataSource{
    suspend fun getCurrentWeather(latitude:String, longitude:String): Flow<List<Weather>?>
}