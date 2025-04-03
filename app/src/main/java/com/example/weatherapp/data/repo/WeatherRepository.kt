package com.example.weatherapp.data.repo

import android.content.Context
import com.example.weatherapp.Settings.Constants.TempUnit
import com.example.weatherapp.data.models.CurrentWeatherResponse
import com.example.weatherapp.data.models.WeatherForecastResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude:Double, longitude:Double, apiKey: String, units: String, language: String): Flow<CurrentWeatherResponse?>
    suspend fun getWeatherForecast(latitude:Double, longitude:Double, apiKey: String, units: String, language: String): Flow<WeatherForecastResponse?>
    suspend fun readTempUnit(context: Context) : Flow<String>
    suspend fun writeTempUnit(tempUnit:String,context: Context)

}
