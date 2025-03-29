package com.example.weatherapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp.currentweather.HomeFactory
import com.example.weatherapp.currentweather.HomeView
import com.example.weatherapp.currentweather.HomeViewModel
import com.example.weatherapp.data.remote.RetrofitHelper
import com.example.weatherapp.data.remote.WeatherRemoteDataSourceImpl
import com.example.weatherapp.data.repo.WeatherRepositoryImpl

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: NavigationRoute,

){
    NavHost(
        navController= navController
        , startDestination = startDestination
    ){
        composable<NavigationRoute.Home>(){
            val latitude:Double = 26.820553
            val longitude:Double = 30.802498
            HomeView(ViewModelProvider(
                LocalContext.current as ViewModelStoreOwner,
                HomeFactory(
                    WeatherRepositoryImpl.getInstance(
                        WeatherRemoteDataSourceImpl(RetrofitHelper.apiService)

                    )
                )
            ).get(HomeViewModel::class.java),
                latitude,
                longitude
            )
        }

    }
}

