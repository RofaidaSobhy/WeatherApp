package com.example.weatherapp.utils

object WindSpeedUtils {
    fun mpsToMph(mps: Double): Double {
        return mps * 2.23694
    }
}