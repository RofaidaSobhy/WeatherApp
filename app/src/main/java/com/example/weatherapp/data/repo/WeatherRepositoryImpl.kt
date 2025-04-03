package com.example.weatherapp.data.repo


import com.example.weatherapp.data.local.LocalDataSource
import com.example.weatherapp.data.models.CurrentWeatherResponse
import com.example.weatherapp.data.models.WeatherForecastResponse
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl private constructor(
    private val remoteDataSource:WeatherRemoteDataSource,
    private val localDataSource: LocalDataSource
)
    :WeatherRepository {
    override suspend fun getCurrentWeather(latitude:Double, longitude:Double, apiKey: String , units: String , language: String ): Flow<CurrentWeatherResponse?> {
        return remoteDataSource.getCurrentWeather(latitude,longitude,apiKey,units,language)
    }
    override suspend fun getWeatherForecast(latitude:Double, longitude:Double, apiKey: String , units: String , language: String ): Flow<WeatherForecastResponse?> {
        return remoteDataSource.getWeatherForecast(latitude,longitude,apiKey,units,language)
    }

    override suspend fun readTempUnit(): Flow<String>  {
        return localDataSource.readTempUnit()

    }


    override suspend fun writeTempUnit(tempUnit: String) {
        localDataSource.writeTempUnit(tempUnit)

    }

    override suspend fun readWindSpeedUnit(): Flow<String> {
        return localDataSource.readWindSpeedUnit()
    }

    override suspend fun writeWindSpeedUnit(windSpeedUnit: String) {
        localDataSource.writeWindSpeedUnit(windSpeedUnit)
    }


    override suspend fun readOldTempUnit(): Flow<String> {
        return localDataSource.readOldTempUnit()
    }

    override suspend fun writeOldTempUnit(oldTempUnit: String) {
        localDataSource.writeOldTempUnit(oldTempUnit)
    }

    companion object{
        private var INSTANCE : WeatherRepositoryImpl? = null
        fun getInstance(remoteDataSource: WeatherRemoteDataSource,localDataSource: LocalDataSource): WeatherRepository{
            return INSTANCE ?: synchronized(this){
                val temp = WeatherRepositoryImpl(remoteDataSource,localDataSource)
                INSTANCE = temp
                temp
            }
        }
    }
}