package com.example.weatherapp.data.local

import com.example.weatherapp.data.models.FavLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeLocalDataSource(
    private val favLocations: MutableList<FavLocation> = mutableListOf()
):LocalDataSource {
    private val favLocationsFlow = MutableStateFlow<List<FavLocation>>(favLocations)

    override suspend fun readTempUnit(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeTempUnit(tempUnit: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readWindSpeedUnit(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeWindSpeedUnit(windSpeedUnit: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readLocationMethod(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeLocationMethod(locationMethod: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readLatitude(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeLatitude(latitude: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readLongitude(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeLongitude(longitude: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readLanguage(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeLanguage(language: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readOldTempUnit(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeOldTempUnit(oldTempUnit: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavLocations(): Flow<List<FavLocation>> {
        return favLocationsFlow.asStateFlow()


    }

    override suspend fun insertFavLocation(favLocation: FavLocation): Long {
        return if (favLocations.add(favLocation)) {
            favLocationsFlow.value = favLocations
            1L
        } else {
            -1L
        }
    }

    override suspend fun deleteFavLocation(favLocation: FavLocation?): Int {
        return if (favLocations.remove(favLocation)) {
            favLocationsFlow.value = favLocations
            1
        } else {
            0
        }

    }

}