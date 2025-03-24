package com.example.weatherapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather?appid=dbc27c07dc760401e546041052488210")
    suspend fun getCurrentWeather(@Query("lat") latitude: String,@Query("lon") longitude: String): Response<WeatherResponse>
}