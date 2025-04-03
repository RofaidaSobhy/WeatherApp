package com.example.weatherapp.data.repo

import android.content.Context
import com.example.weatherapp.Settings.Constants.TempUnit
import com.example.weatherapp.data.models.CurrentWeatherResponse
import com.example.weatherapp.data.models.WeatherForecastResponse
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import com.example.weatherapp.utils.Constants.TEMP_UNIT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl private constructor(
    private val remoteDataSource:WeatherRemoteDataSource,
//    private val localDataSource: SettingsLocalDataSource
)
    :WeatherRepository {
    override suspend fun getCurrentWeather(latitude:Double, longitude:Double, apiKey: String , units: String , language: String ): Flow<CurrentWeatherResponse?> {
        return remoteDataSource.getCurrentWeather(latitude,longitude,apiKey,units,language)
    }
    override suspend fun getWeatherForecast(latitude:Double, longitude:Double, apiKey: String , units: String , language: String ): Flow<WeatherForecastResponse?> {
        return remoteDataSource.getWeatherForecast(latitude,longitude,apiKey,units,language)
    }

    override suspend fun readTempUnit(context: Context): Flow<String> = flow {
        val sharedPreferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

        val tempUint = sharedPreferences.getString(TEMP_UNIT, TempUnit.celsius) ?: TempUnit.celsius
        emit(tempUint)

    }

    override suspend fun writeTempUnit(tempUnit: String,context: Context) {
        val sharedPreferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

        sharedPreferences.edit().putString(TEMP_UNIT, tempUnit).apply()
    }

    companion object{
        private var INSTANCE : WeatherRepositoryImpl? = null
        fun getInstance(remoteDataSource: WeatherRemoteDataSource,): WeatherRepository{
            return INSTANCE ?: synchronized(this){
                val temp = WeatherRepositoryImpl(remoteDataSource)
                INSTANCE = temp
                temp
            }
        }
    }
}