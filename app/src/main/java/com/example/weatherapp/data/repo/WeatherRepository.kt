package com.example.weatherapp.data.repo

import com.example.weatherapp.data.models.CurrentWeatherResponse
import com.example.weatherapp.data.models.FavLocation
import com.example.weatherapp.data.models.WeatherForecastResponse
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude:Double, longitude:Double, apiKey: String, units: String, language: String): Flow<CurrentWeatherResponse?>
    suspend fun getWeatherForecast(latitude:Double, longitude:Double, apiKey: String, units: String, language: String): Flow<WeatherForecastResponse?>
    suspend fun readTempUnit() : Flow<String>
    suspend fun writeTempUnit(tempUnit:String)
    suspend fun readWindSpeedUnit() : Flow<String>
    suspend fun writeWindSpeedUnit(windSpeedUnit:String)
    suspend fun readLocationMethod() : Flow<String>
    suspend fun writeLocationMethod(locationMethod:String)
    suspend fun readLatitude(): Flow<String>
    suspend fun writeLatitude(latitude: String)
    suspend fun readLongitude(): Flow<String>
    suspend fun writeLongitude(longitude: String)
    suspend fun readOldTempUnit() : Flow<String>
    suspend fun writeOldTempUnit(oldTempUnit:String)


    suspend fun getFavLocations(): Flow<List<FavLocation>?>
    suspend fun addFavLocation(favLocation: FavLocation):Long
    suspend fun removeFavLocation(favLocation: FavLocation):Int
}
