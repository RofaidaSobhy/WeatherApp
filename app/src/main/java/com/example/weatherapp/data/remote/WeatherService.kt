package com.example.weatherapp.data.remote

import com.example.weatherapp.data.models.CurrentWeatherResponse
import com.example.weatherapp.data.models.WeatherForecastResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String ,
        @Query("units") units: String,
        @Query("lang") language: String
    ): Response<CurrentWeatherResponse>

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String ,
        @Query("units") units: String,
        @Query("lang") language: String
    ) :Response<WeatherForecastResponse>
}