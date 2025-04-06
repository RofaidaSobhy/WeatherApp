package com.example.weatherapp

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
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
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.data.models.ReminderItem
import com.example.weatherapp.navigation.NavGraph
import com.example.weatherapp.navigation.NavigationRoute
import com.example.weatherapp.notification.helpers.scheduler.NotificationAlarmScheduler
import com.example.weatherapp.utils.Constants.REQUEST_LOCATION_CODE
import com.example.weatherapp.utils.LocationUtils.checkedPermissions
import com.example.weatherapp.utils.LocationUtils.enableLocationServices
import com.example.weatherapp.utils.LocationUtils.isLocationEnabled
import com.example.weatherapp.utils.ManifestUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.libraries.places.api.Places
import java.util.Calendar

class MainActivity : ComponentActivity() {
    private val notificationAlarmScheduler by lazy {
        NotificationAlarmScheduler(this)
    }
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationState: MutableState<Location>
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /*val reminderItem = ReminderItem(
            id = 200,
            startTime =  Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 5)
                set(Calendar.MINUTE, 12)
            }.timeInMillis,
            latitude = 30.06263,
            longitude = 31.24967
        )


        notificationAlarmScheduler.schedule(reminderItem)*/
        // Retrieve the API key from the manifest file
        val apiKey = ManifestUtils.getApiKeyFromManifest(this)
        // Initialize the Places API with the retrieved API key
        if (!Places.isInitialized() && apiKey != null) {
            Places.initialize(applicationContext, apiKey)
        }


        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF1B1D1E)
            ) {
                val navController = rememberNavController()
                locationState = remember{ mutableStateOf(Location(LocationManager.GPS_PROVIDER)) }
                val latitude:Double = locationState.value.latitude
                val longitude:Double = locationState.value.longitude
                NavGraph(navController = navController, NavigationRoute.Home(latitude,longitude),latitude,longitude ,notificationAlarmScheduler)
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


