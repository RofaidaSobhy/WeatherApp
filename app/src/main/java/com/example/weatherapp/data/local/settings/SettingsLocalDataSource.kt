package com.example.weatherapp.data.local.settings

import kotlinx.coroutines.flow.Flow

interface SettingsLocalDataSource {
    suspend fun readTempUnit(): Flow<String>
    suspend fun writeTempUnit(tempUnit: String)
}