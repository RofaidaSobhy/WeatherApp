package com.example.weatherapp.data.repo.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun readTempUnit() : Flow<String>
    suspend fun writeTempUnit(tempUnit:String)
}