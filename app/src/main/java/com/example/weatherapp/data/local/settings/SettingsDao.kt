package com.example.weatherapp.data.local.settings

import kotlinx.coroutines.flow.Flow

interface SettingsDao {
    fun readTempUnit(): Flow<String>
    suspend fun writeTempUnit(tempUnit: String)
    fun readWindSpeedUnit(): Flow<String>
    suspend fun writeWindSpeedUnit(windSpeedUnit: String)
    fun readLocationMethod(): Flow<String>
    suspend fun writeLocationMethod(locationMethod: String)
    fun readLatitude(): Flow<String>
    suspend fun writeLatitude(latitude: String)
    fun readLongitude(): Flow<String>
    suspend fun writeLongitude(longitude: String)
    fun readOldTempUnit(): Flow<String>
    suspend fun writeOldTempUnit(tempUnit: String)
}