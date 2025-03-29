package com.example.weatherapp.data.repo

import com.example.weatherapp.data.models.CurrentWeatherResponse
import com.example.weatherapp.data.models.WeatherForecastResponse
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl private constructor(
    private val remoteDataSource:WeatherRemoteDataSource )
    :WeatherRepository {
    override suspend fun getCurrentWeather(latitude:Double, longitude:Double, apiKey: String , units: String , language: String ): Flow<CurrentWeatherResponse?> {
        return remoteDataSource.getCurrentWeather(latitude,longitude,apiKey,units,language)
    }
    override suspend fun getWeatherForecast(latitude:Double, longitude:Double, apiKey: String , units: String , language: String ): Flow<WeatherForecastResponse?> {
        return remoteDataSource.getWeatherForecast(latitude,longitude,apiKey,units,language)
    }

    companion object{
        private var INSTANCE : WeatherRepositoryImpl? = null
        fun getInstance(remoteDataSource: WeatherRemoteDataSource): WeatherRepository{
            return INSTANCE ?: synchronized(this){
                val temp = WeatherRepositoryImpl(remoteDataSource)
                INSTANCE = temp
                temp
            }
        }
    }
}