package com.example.weatherapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoute {
    @Serializable
    data class Home(val latitude:Double, val longitude:Double):NavigationRoute(){

    }
}