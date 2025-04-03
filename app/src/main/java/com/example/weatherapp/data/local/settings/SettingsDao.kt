package com.example.weatherapp.data.local.settings

import kotlinx.coroutines.flow.Flow

interface SettingsDao {
    fun readTempUnit(): Flow<String>
    suspend fun writeTempUnit(tempUnit: String)
    fun readWindSpeedUnit(): Flow<String>
    suspend fun writeWindSpeedUnit(windSpeedUnit: String)
    fun readOldTempUnit(): Flow<String>
    suspend fun writeOldTempUnit(tempUnit: String)
}