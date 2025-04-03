package com.example.weatherapp.data.local

import com.example.weatherapp.data.local.settings.SettingsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val settingsDao: SettingsDao): LocalDataSource {
    override suspend fun readTempUnit(): Flow<String> {
        return settingsDao.readTempUnit()
    }
    override suspend fun writeTempUnit(tempUnit: String) {
        settingsDao.writeTempUnit(tempUnit)
    }

    override suspend fun readOldTempUnit(): Flow<String> {
        return settingsDao.readOldTempUnit()
    }

    override suspend fun writeOldTempUnit(oldTempUnit: String) {
        settingsDao.writeTempUnit(oldTempUnit)
    }
}