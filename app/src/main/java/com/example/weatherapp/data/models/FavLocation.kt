package com.example.weatherapp.data.models

import androidx.room.Entity

@Entity(primaryKeys = ["latitude", "longitude"])
data class FavLocation(
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val city: String
)