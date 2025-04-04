package com.example.weatherapp.data.local.settings

import android.content.SharedPreferences
import com.example.weatherapp.settings.Constants.Location
import com.example.weatherapp.settings.Constants.TempUnit
import com.example.weatherapp.settings.Constants.WindSpeedUnit
import com.example.weatherapp.utils.Constants.LATITUDE
import com.example.weatherapp.utils.Constants.LOCATION_METHOD
import com.example.weatherapp.utils.Constants.LONGITUDE
import com.example.weatherapp.utils.Constants.OLD_TEMP_UNIT
import com.example.weatherapp.utils.Constants.TEMP_UNIT
import com.example.weatherapp.utils.Constants.WIND_SPEED_UNIT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SettingsDaoImpl(private val sharedPreferences: SharedPreferences) : SettingsDao {

    override fun readTempUnit(): Flow<String> = flow {
        val tempUint = sharedPreferences.getString(TEMP_UNIT, TempUnit.kelvin) ?: TempUnit.kelvin
        emit(tempUint)
    }

    override suspend fun writeTempUnit(tempUnit: String) {
        sharedPreferences.edit().putString(TEMP_UNIT, tempUnit).apply()
    }

    override fun readWindSpeedUnit(): Flow<String> = flow {
        val windSpeedUint = sharedPreferences.getString(WIND_SPEED_UNIT, WindSpeedUnit.meter) ?: WindSpeedUnit.meter
        emit(windSpeedUint)
    }

    override suspend fun writeWindSpeedUnit(windSpeedUnit: String) {
        sharedPreferences.edit().putString(WIND_SPEED_UNIT, windSpeedUnit).apply()
    }

    override fun readLocationMethod(): Flow<String> = flow {
        val locationMethod = sharedPreferences.getString(LOCATION_METHOD, Location.gps) ?: Location.gps
        emit(locationMethod)
    }

    override suspend fun writeLocationMethod(locationMethod: String) {
        sharedPreferences.edit().putString(LOCATION_METHOD, locationMethod).apply()
    }

    override fun readLatitude(): Flow<String> = flow {
        val latitude = sharedPreferences.getString(LATITUDE, "0.0") ?: "0.0"
        emit(latitude)
    }

    override suspend fun writeLatitude(latitude: String) {
        sharedPreferences.edit().putString(LATITUDE, latitude).apply()
    }

    override fun readLongitude(): Flow<String> = flow {
        val longitude = sharedPreferences.getString(LONGITUDE, "0.0") ?: "0.0"
        emit(longitude)
    }

    override suspend fun writeLongitude(longitude: String) {
        sharedPreferences.edit().putString(LONGITUDE, longitude).apply()
    }

    override fun readOldTempUnit(): Flow<String> = flow {
        val tempUint = sharedPreferences.getString(OLD_TEMP_UNIT, TempUnit.kelvin) ?: TempUnit.kelvin
        emit(tempUint)
    }

    override suspend fun writeOldTempUnit(oldTempUnit: String) {
        sharedPreferences.edit().putString(OLD_TEMP_UNIT, oldTempUnit).apply()
    }
}