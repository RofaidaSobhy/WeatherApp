package com.example.weatherapp.data.local.settings

import android.content.SharedPreferences
import com.example.weatherapp.Settings.Constants.TempUnit
import com.example.weatherapp.Settings.Constants.WindSpeedUnit
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

    override fun readOldTempUnit(): Flow<String> = flow {
        val tempUint = sharedPreferences.getString(OLD_TEMP_UNIT, TempUnit.kelvin) ?: TempUnit.kelvin
        emit(tempUint)
    }

    override suspend fun writeOldTempUnit(oldTempUnit: String) {
        sharedPreferences.edit().putString(OLD_TEMP_UNIT, oldTempUnit).apply()
    }
}