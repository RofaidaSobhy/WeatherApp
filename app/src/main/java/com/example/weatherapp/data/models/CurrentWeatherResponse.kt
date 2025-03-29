package com.example.weatherapp.data.models


data class CurrentWeatherResponse(
    var coord: Coord? = null,
    var weather: ArrayList<Weather>? = null,
    var base: String? = null,
    var main: Main? = null,
    var visibility: Int = 0,
    var wind: Wind? = null,
    var clouds: Clouds? = null,
    var dt: Int = 0,
    var sys: Sys? = null,
    var timezone: Int = 0,
    var id: Int = 0,
    var name: String? = null,
    var cod: Int = 0
)

