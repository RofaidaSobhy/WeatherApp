package com.example.weatherapp.data.local

import com.example.weatherapp.data.models.FavLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow

class StubLocalDataSource(
    private var favLocations: MutableStateFlow<List<FavLocation>> ?= MutableStateFlow(emptyList())
):LocalDataSource {
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

    override suspend fun readOldTempUnit(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun writeOldTempUnit(oldTempUnit: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavLocations(): Flow<List<FavLocation>> {
        return favLocations ?: MutableStateFlow(emptyList())
    }

    override suspend fun insertFavLocation(favLocation: FavLocation): Long {
        favLocations?.value = favLocations?.value?.plus(favLocation) ?: listOf(favLocation)
        return 1L
    }

    override suspend fun deleteFavLocation(favLocation: FavLocation?): Int {
        favLocations?.value = favLocations?.value?.filter { it != favLocation } ?: emptyList()
        return 1
    }

}