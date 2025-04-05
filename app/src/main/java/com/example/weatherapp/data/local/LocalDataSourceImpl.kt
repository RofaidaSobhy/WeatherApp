package com.example.weatherapp.data.local

import com.example.weatherapp.data.local.favlocation.FavLocationDao
import com.example.weatherapp.data.local.settings.SettingsDao
import com.example.weatherapp.data.models.FavLocation
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val settingsDao: SettingsDao, private val favLocationDao : FavLocationDao): LocalDataSource {
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

    override suspend fun getFavLocations(): Flow<List<FavLocation>> {
        return favLocationDao.getAllFavoriteLocations()
    }

    override suspend fun insertFavLocation(favLocation: FavLocation): Long {
        return favLocationDao.insertFavLocation(favLocation)
    }

    override suspend fun deleteFavLocation(favLocation: FavLocation?): Int {
        return if(favLocation!=null)
            favLocationDao.deleteFavLocation(favLocation)
        else
            -1
    }

}