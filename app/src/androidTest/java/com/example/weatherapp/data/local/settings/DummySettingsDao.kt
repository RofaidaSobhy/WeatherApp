package com.example.weatherapp.data.local.settings

import kotlinx.coroutines.flow.Flow

class DummySettingsDao() :SettingsDao {
    override fun readTempUnit(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeTempUnit(tempUnit: String) {
        TODO("Not yet implemented")
    }

    override fun readWindSpeedUnit(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeWindSpeedUnit(windSpeedUnit: String) {
        TODO("Not yet implemented")
    }

    override fun readLocationMethod(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeLocationMethod(locationMethod: String) {
        TODO("Not yet implemented")
    }

    override fun readLatitude(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeLatitude(latitude: String) {
        TODO("Not yet implemented")
    }

    override fun readLongitude(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeLongitude(longitude: String) {
        TODO("Not yet implemented")
    }

    override fun readLanguage(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeLanguage(language: String) {
        TODO("Not yet implemented")
    }

    override fun readOldTempUnit(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeOldTempUnit(tempUnit: String) {
        TODO("Not yet implemented")
    }
}