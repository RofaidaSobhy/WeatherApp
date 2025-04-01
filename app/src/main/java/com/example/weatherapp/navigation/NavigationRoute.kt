package com.example.weatherapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoute {

    companion object {
        fun fromRoute(route: String): NavigationRoute? {
            return NavigationRoute::class.sealedSubclasses.firstOrNull {
                route.contains(it.qualifiedName.toString())
            }?.objectInstance
        }
    }
    @Serializable
    data class Home(val latitude:Double = 0.0, val longitude:Double = 0.0):NavigationRoute()

    @Serializable
    object Settings: NavigationRoute()
}