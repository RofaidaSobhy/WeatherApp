package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.currentweather.HomeFactory
import com.example.weatherapp.currentweather.HomeViewModel
import com.example.weatherapp.data.local.LocalDataSourceImpl
import com.example.weatherapp.data.local.settings.SettingsDaoImpl
import com.example.weatherapp.data.remote.RetrofitHelper
import com.example.weatherapp.data.remote.WeatherRemoteDataSourceImpl
import com.example.weatherapp.data.repo.WeatherRepositoryImpl
import com.example.weatherapp.navigation.NavGraph
import com.example.weatherapp.navigation.NavigationRoute
import com.example.weatherapp.utils.Constants.REQUEST_LOCATION_CODE
import com.example.weatherapp.utils.LocationUtils.checkedPermissions
import com.example.weatherapp.utils.LocationUtils.enableLocationServices
import com.example.weatherapp.utils.LocationUtils.isLocationEnabled
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationState: MutableState<Location>
    private lateinit var viewModel: HomeViewModel


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =HomeViewModel(

            WeatherRepositoryImpl.getInstance(
                WeatherRemoteDataSourceImpl(RetrofitHelper.apiService)
                , LocalDataSourceImpl(
                    SettingsDaoImpl(this.getSharedPreferences("AppSettings", Context.MODE_PRIVATE))
                )

            )
        )
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF1B1D1E)
            ) {
                val navController = rememberNavController()
                locationState = remember{ mutableStateOf(Location(LocationManager.GPS_PROVIDER)) }
                val latitude:Double = locationState.value.latitude
                val longitude:Double = locationState.value.longitude

                viewModel.setLongitude(longitude)
                viewModel.setLatitude(latitude)
                Log.i("TAG", "onCreate: latitude = $latitude ")
                Log.i("TAG", "onCreate: longitude = $longitude ")

                    NavGraph(navController = navController, NavigationRoute.Home)


            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(checkedPermissions(this)) {
            if(isLocationEnabled(this)){
                getFreshLocation()
            }else{
                enableLocationServices(this)
            }
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                    , android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_LOCATION_CODE
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun getFreshLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient.requestLocationUpdates(
            LocationRequest.Builder(0).apply {
                setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            }.build(),
            object : LocationCallback(){
                override fun onLocationResult(location: LocationResult) {
                    super.onLocationResult(location)

                    locationState.value = location.lastLocation?: Location(LocationManager.GPS_PROVIDER)
                }
            },
            Looper.myLooper()

        )

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)

        if(requestCode == REQUEST_LOCATION_CODE){
            if(grantResults.get(0) == PackageManager.PERMISSION_GRANTED
                || grantResults.get(1) == PackageManager.PERMISSION_GRANTED){

                if(isLocationEnabled(this)){
                    getFreshLocation()
                }else{
                    enableLocationServices(this)
                }
            }else{
                //need update >> if user refused permissions
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                        , android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    REQUEST_LOCATION_CODE
                )
            }
        }
    }
}


