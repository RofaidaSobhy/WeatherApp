package com.example.weatherapp.data.local

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
    suspend fun readOldTempUnit(): Flow<String>
    suspend fun writeOldTempUnit(oldTempUnit: String)
}