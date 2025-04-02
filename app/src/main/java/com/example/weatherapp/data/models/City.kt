package com.example.weatherapp.data.models


data class City (
    var id: Int,
    var name: String,
    var coord: Coord,
    var country: String,
    var population: Int,
    var timezone: Int,
    var sunrise: Int,
    var sunset: Int
)