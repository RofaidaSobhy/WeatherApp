package com.example.weatherapp.data.local.settings

import android.content.SharedPreferences
import com.example.weatherapp.Settings.Constants.TempUnit
import com.example.weatherapp.utils.Constants.TEMP_UNIT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SettingsDaoImpl(private val sharedPreferences: SharedPreferences) : SettingsDao {

    override fun readTempUnit(): Flow<String> = flow {
        val tempUint = sharedPreferences.getString(TEMP_UNIT, TempUnit.celsius) ?: TempUnit.celsius
        emit(tempUint)
    }

    override suspend fun writeTempUnit(tempUnit: String) {
        sharedPreferences.edit().putString(TEMP_UNIT, tempUnit).apply()
    }
}