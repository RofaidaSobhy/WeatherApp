package com.example.weatherapp.navigation

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.weatherapp.map.MapFactory
import com.example.weatherapp.map.MapView
import com.example.weatherapp.map.MapViewModel
import com.example.weatherapp.R
import com.example.weatherapp.settings.SettingsFactory
import com.example.weatherapp.settings.SettingsView
import com.example.weatherapp.settings.SettingsViewModel
import com.example.weatherapp.home.HomeFactory
import com.example.weatherapp.home.HomeView
import com.example.weatherapp.home.HomeViewModel
import com.example.weatherapp.data.local.LocalDataSourceImpl
import com.example.weatherapp.data.local.favlocation.FavLocationDatabase
import com.example.weatherapp.data.local.settings.SettingsDaoImpl
import com.example.weatherapp.ui.components.BottomNavItem
import com.example.weatherapp.ui.components.BottomNavigationBar
import com.example.weatherapp.data.remote.RetrofitHelper
import com.example.weatherapp.data.remote.WeatherRemoteDataSourceImpl
import com.example.weatherapp.data.repo.WeatherRepositoryImpl
import com.example.weatherapp.favorite.FavoriteFactory
import com.example.weatherapp.favorite.FavoriteView
import com.example.weatherapp.favorite.FavoriteViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: NavigationRoute,
    latitude:Double,
    longitude:Double


){

    val bottomNavItems = remember {
        listOf(
            BottomNavItem(NavigationRoute.Home(), R.drawable.home, "Home"),
            BottomNavItem(NavigationRoute.Favorite, R.drawable.favorite, "Favorite"),
            BottomNavItem(NavigationRoute.Settings, R.drawable.settings, "Settings"),


            )
    }

    val backStackEntry= navController.currentBackStackEntryAsState().value
    var currentRoute = remember(backStackEntry) {
        NavigationRoute.fromRoute(backStackEntry?.destination?.route ?: "")
    }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavItems,
                onItemClick = { index ->
                    val screen = bottomNavItems[index].route
                    navigateToTab(navController, screen)
                },
                selectedItem = currentRoute
            )
        }

    ) { innerPadding ->

        NavHost(
            navController= navController
            , startDestination = startDestination
        ){
            composable<NavigationRoute.Home>(){backStackEntry ->
                val isFromFav = backStackEntry.arguments?.getBoolean("isFromFav") ?: false

                var currentLatitude:Double
                var currentLongitude:Double
                if(isFromFav == true){
                    currentLatitude = backStackEntry.arguments?.getDouble("latitude") ?: 0.0
                    currentLongitude = backStackEntry.arguments?.getDouble("longitude") ?: 0.0
                }else{
                    currentLatitude = latitude
                    currentLongitude = longitude
                }

//                val latitude:Double = 26.820553
//                val longitude:Double = 30.802498
                val context = LocalContext.current
                val sharedPreferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

                HomeView(ViewModelProvider(
                    LocalContext.current as ViewModelStoreOwner,
                    HomeFactory(
                        WeatherRepositoryImpl.getInstance(
                            WeatherRemoteDataSourceImpl(RetrofitHelper.apiService)
                            ,LocalDataSourceImpl(
                                SettingsDaoImpl(sharedPreferences)
                                , FavLocationDatabase.getInstance(context).getFavLocationDao()
                            )

                        )
                    )
                ).get(HomeViewModel::class.java),
                    currentLatitude,
                    currentLongitude,
                    isFromFav
                )

            }

            composable<NavigationRoute.Settings> {
                val context = LocalContext.current
                val sharedPreferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
                SettingsView(
                    ViewModelProvider(
                        LocalContext.current as ViewModelStoreOwner,
                        SettingsFactory(
                           WeatherRepositoryImpl.getInstance(
                               WeatherRemoteDataSourceImpl(RetrofitHelper.apiService)
                               ,LocalDataSourceImpl(
                                   SettingsDaoImpl(sharedPreferences)
                                   , FavLocationDatabase.getInstance(context).getFavLocationDao()
                               )

                            )
                        )
                    ).get(SettingsViewModel::class.java)
                    ,
                    {navController.navigate(NavigationRoute.Map("Select"))}
                )
            }

            composable<NavigationRoute.Map> {backStackEntry ->
                // Retrieve the actionName parameter passed from the previous screen
                val actionName = backStackEntry.arguments?.getString("actionName") ?: "Add To Favorite"
                val context = LocalContext.current
                val sharedPreferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
                MapView(
                    ViewModelProvider(
                        LocalContext.current as ViewModelStoreOwner,
                        MapFactory(
                            WeatherRepositoryImpl.getInstance(
                                WeatherRemoteDataSourceImpl(RetrofitHelper.apiService)
                                ,LocalDataSourceImpl(
                                    SettingsDaoImpl(sharedPreferences)
                                    , FavLocationDatabase.getInstance(context).getFavLocationDao()
                                )

                            )
                        )

                    ).get(MapViewModel::class.java)
                    , actionName
                    ,{ route :NavigationRoute , inclusive:Boolean ->navController.popBackStack(route,inclusive)}
                )
            }

            composable<NavigationRoute.Favorite> {
                val context = LocalContext.current
                val sharedPreferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
                FavoriteView(
                    ViewModelProvider(
                        LocalContext.current as ViewModelStoreOwner,
                        FavoriteFactory(
                            WeatherRepositoryImpl.getInstance(
                                WeatherRemoteDataSourceImpl(RetrofitHelper.apiService)
                                ,LocalDataSourceImpl(
                                    SettingsDaoImpl(sharedPreferences)
                                    , FavLocationDatabase.getInstance(context).getFavLocationDao()
                                )

                            )
                        )
                    ).get(FavoriteViewModel::class.java)
                    ,{navController.navigate(NavigationRoute.Map("Add To Favorite"))}
                    , {
                        latitude:Double, longitude:Double ->
                        Log.i("TAG", "NavGraph: From fav:Latitude = $latitude, longitude = $longitude ")
                        navController.navigate(NavigationRoute.Home(latitude,longitude,true))
                    }
                )
            }

        }
    }

}

private fun navigateToTab(navController: NavController, screen: NavigationRoute) {
    navController.navigate(screen) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
