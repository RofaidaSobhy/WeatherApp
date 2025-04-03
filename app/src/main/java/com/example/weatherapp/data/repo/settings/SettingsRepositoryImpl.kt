package com.example.weatherapp.data.repo.settings

import com.example.weatherapp.data.local.settings.SettingsLocalDataSource
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl private constructor( private val settingsDataSource: SettingsLocalDataSource) :SettingsRepository {
    override suspend fun readTempUnit(): Flow<String> {
        return settingsDataSource.readTempUnit()
    }

    override suspend fun writeTempUnit(tempUnit: String) {
        settingsDataSource.writeTempUnit(tempUnit)
    }

    companion object{
        private var INSTANCE : SettingsRepositoryImpl? = null
        fun getInstance(settingsDataSource: SettingsLocalDataSource): SettingsRepository {
            return INSTANCE ?: synchronized(this){
                val temp = SettingsRepositoryImpl(settingsDataSource)
                INSTANCE = temp
                temp
            }
        }
    }
}