package com.example.weatherapp.data.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun readTempUnit(): Flow<String>
    suspend fun writeTempUnit(tempUnit: String)
}