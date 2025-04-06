package com.example.weatherapp.data.local

import com.example.weatherapp.data.models.FavLocation
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun readTempUnit(): Flow<String>
    suspend fun writeTempUnit(tempUnit: String)
    suspend fun readWindSpeedUnit(): Flow<String>
    suspend fun writeWindSpeedUnit(windSpeedUnit: String)
    suspend fun readLocationMethod(): Flow<String>
    suspend fun writeLocationMethod(locationMethod: String)
    suspend fun readLatitude(): Flow<String>
    suspend fun writeLatitude(latitude: String)
    suspend fun readLongitude(): Flow<String>
    suspend fun writeLongitude(longitude: String)
    suspend fun readLanguage(): Flow<String>
    suspend fun writeLanguage(language: String)
    suspend fun readOldTempUnit(): Flow<String>
    suspend fun writeOldTempUnit(oldTempUnit: String)




    suspend fun getFavLocations(): Flow<List<FavLocation>>
    suspend fun insertFavLocation(favLocation: FavLocation): Long
    suspend fun deleteFavLocation(favLocation: FavLocation?): Int
}