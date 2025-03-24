package com.example.weatherapp.data.repo

import com.example.weatherapp.data.models.Weather
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude:String, longitude:String): Flow<List<Weather>?>

}
