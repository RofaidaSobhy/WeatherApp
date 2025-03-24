package com.example.weatherapp.data.repo

import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl private constructor(
    private val remoteDataSource:WeatherRemoteDataSource )
    :WeatherRepository {
    override suspend fun getCurrentWeather(latitude:String, longitude:String): Flow<List<Weather>?> {
        return remoteDataSource.getCurrentWeather(latitude,longitude)
    }
}