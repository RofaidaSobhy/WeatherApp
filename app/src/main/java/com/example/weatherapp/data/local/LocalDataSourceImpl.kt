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

    override suspend fun readWindSpeedUnit(): Flow<String> {
        return settingsDao.readWindSpeedUnit()
    }

    override suspend fun writeWindSpeedUnit(windSpeedUnit: String) {
        settingsDao.writeWindSpeedUnit(windSpeedUnit)
    }

    override suspend fun readLocationMethod(): Flow<String> {
        return settingsDao.readLocationMethod()
    }

    override suspend fun writeLocationMethod(locationMethod: String) {
        settingsDao.writeLocationMethod(locationMethod)
    }

    override suspend fun readLatitude(): Flow<String> {
        return settingsDao.readLatitude()
    }

    override suspend fun writeLatitude(latitude: String) {
        settingsDao.writeLatitude(latitude)
    }

    override suspend fun readLongitude(): Flow<String> {
        return settingsDao.readLongitude()
    }

    override suspend fun writeLongitude(longitude: String) {
        settingsDao.writeLongitude(longitude)
    }

    override suspend fun readOldTempUnit(): Flow<String> {
        return settingsDao.readOldTempUnit()
    }

    override suspend fun writeOldTempUnit(oldTempUnit: String) {
        settingsDao.writeTempUnit(oldTempUnit)
    }
}