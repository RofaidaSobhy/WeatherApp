package com.example.weatherapp.data.repo


import com.example.weatherapp.data.local.LocalDataSource
import com.example.weatherapp.data.models.CurrentWeatherResponse
import com.example.weatherapp.data.models.FavLocation
import com.example.weatherapp.data.models.WeatherForecastResponse
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl (
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

    override suspend fun readLocationMethod(): Flow<String> {
        return localDataSource.readLocationMethod()
    }

    override suspend fun writeLocationMethod(locationMethod: String) {
        localDataSource.writeLocationMethod(locationMethod)
    }

    override suspend fun readLatitude(): Flow<String> {
        return localDataSource.readLatitude()
    }

    override suspend fun writeLatitude(latitude: String) {
        localDataSource.writeLatitude(latitude)
    }

    override suspend fun readLongitude(): Flow<String> {
        return localDataSource.readLongitude()
    }

    override suspend fun writeLongitude(longitude: String) {
        localDataSource.writeLongitude(longitude)
    }

    override suspend fun readLanguage(): Flow<String> {
        return localDataSource.readLanguage()
    }

    override suspend fun writeLanguage(language: String) {
        localDataSource.writeLanguage(language)
    }


    override suspend fun readOldTempUnit(): Flow<String> {
        return localDataSource.readOldTempUnit()
    }

    override suspend fun writeOldTempUnit(oldTempUnit: String) {
        localDataSource.writeOldTempUnit(oldTempUnit)
    }

    override suspend fun getFavLocations(): Flow<List<FavLocation>?> {
        return localDataSource.getFavLocations()
    }

    override suspend fun addFavLocation(favLocation: FavLocation): Long {
        return  localDataSource.insertFavLocation(favLocation)
    }

    override suspend fun removeFavLocation(favLocation: FavLocation): Int {
        return  localDataSource.deleteFavLocation(favLocation)
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