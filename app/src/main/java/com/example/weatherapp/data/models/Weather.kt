package com.example.weatherapp.data.models;

data class Weather (
    var id: Int = 0,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null
)