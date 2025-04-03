package com.example.weatherapp.data.local.settings

import kotlinx.coroutines.flow.Flow

class SettingsLocalDataSourceImpl(private val dao : SettingsDao): SettingsLocalDataSource {
    override suspend fun readTempUnit(): Flow<String> {
        return dao.readTempUnit()
    }

    override suspend fun writeTempUnit(tempUnit: String) {
        dao.writeTempUnit(tempUnit)
    }

    override suspend fun readWindSpeedUnit(): Flow<String> {
        return dao.readWindSpeedUnit()
    }

    override suspend fun writeWindSpeedUnit(windSpeedUnit: String) {
        dao.writeWindSpeedUnit(windSpeedUnit)
    }
}