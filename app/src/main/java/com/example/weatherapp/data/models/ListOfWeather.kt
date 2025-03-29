package com.example.weatherapp.data.models

data class ListOfWeather (
    var dt: Int,
    var main: Main,
    var weather: ArrayList<Weather>,
    var clouds: Clouds,
    var wind: Wind,
    var visibility: Int,
    var pop: Double,
    var sys: Sys,
    var dt_txt: String
)