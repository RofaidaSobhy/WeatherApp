package com.example.weatherapp.utils

import com.example.weatherapp.R
import com.example.weatherapp.data.enums.WeatherType

object WeatherUtils {


    fun getWeatherImage(id: Int): Int {
        val weatherType = getWeatherType(id)

        return when (weatherType) {
            WeatherType.THUNDER -> R.drawable.thunder
            WeatherType.RAIN -> R.drawable.rain
            WeatherType.SNOW -> R.drawable.snow
            WeatherType.ATMOSPHERE -> R.drawable.atmosphere
            WeatherType.CLEAR -> R.drawable.clear
            WeatherType.CLOUDS -> R.drawable.clouds
        }
    }

    private fun getWeatherType(id: Int): WeatherType {
        return when (id) {
            in 200..202, in 210..221, in 230..232, 781 -> WeatherType.THUNDER
            in 300..321, in 500..504, in 520..531 -> WeatherType.RAIN
            511, in 600..602, in 611..622 -> WeatherType.SNOW
            701, 711, 721, 731, in 751..761, in 762..771 -> WeatherType.ATMOSPHERE
            800 -> WeatherType.CLEAR
            in 801..804 -> WeatherType.CLOUDS
            else -> WeatherType.CLEAR
        }
    }
}