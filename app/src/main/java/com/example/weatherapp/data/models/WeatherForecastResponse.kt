package com.example.weatherapp.data.models


data class WeatherForecastResponse (
    var cod: String,
    var message: Int,
    var cnt: Int,
    var list: ArrayList<ListOfWeather>,
    var city: City
)