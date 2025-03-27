package com.example.weatherapp.data.remote

import com.example.weatherapp.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofitInstance = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        //.create(WeatherService::class.java)
    val apiService = retrofitInstance.create(WeatherService::class.java)
}