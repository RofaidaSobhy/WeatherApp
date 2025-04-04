package com.example.weatherapp.Map

import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.weatherapp.navigation.NavigationRoute
import com.example.weatherapp.ui.components.SearchBar
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import timber.log.Timber

@Composable
fun MapView(viewModel: MapViewModel , action: (NavigationRoute, Boolean)->Boolean) {
    // Initialize the camera position state
    val cameraPositionState = rememberCameraPositionState()/*{
        position = CameraPosition.fromLatLngZoom(LatLng(51.5074, -0.1278), 10f)

    }*/

    // Obtain the current context
    val context = LocalContext.current

    // Observe the user's location from the ViewModel
    val userLocation by viewModel.userLocation
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    // Observe the selected location from the ViewModel
    val selectedLocation by viewModel.selectedLocation

    // Handle permission requests for accessing fine location
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Fetch the user's location and update the camera if permission is granted
            viewModel.fetchUserLocation(context, fusedLocationClient)
        } else {
            // Handle the case when permission is denied
            Timber.e("Location permission was denied by the user.")
        }
    }

    // Request the location permission when the composable is launched
    LaunchedEffect(Unit) {
        when (PackageManager.PERMISSION_GRANTED) {
            // Check if the location permission is already granted
            ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) -> {
                // Fetch the user's location and update the camera
                viewModel.fetchUserLocation(context, fusedLocationClient)
            }
            else -> {
                // Request the location permission if it has not been granted
                permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF1B1D1E))) {

        Spacer(modifier = Modifier.height(18.dp))

        SearchBar(
            onPlaceSelected = { place ->
                viewModel.selectLocation(place, context)
            }
        )

        // Display the Google Map
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(550.dp)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxWidth(),
                cameraPositionState = cameraPositionState,
                onMapClick = { latLng ->
                    viewModel.setSelectedLocation(latLng)

                }
            ) {


                selectedLocation?.let { location ->
                    Marker(
                        state = MarkerState(position = location), // Place the marker at the selected location
                        title = "Selected Location", // Set the title for the marker
                        snippet = "This is the place you selected." // Set the snippet for the marker
                    )
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 15f)

                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
        Button(
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF222A36),
                contentColor = Color(0xFFFFFFFF)
            ),
            onClick = {
                selectedLocation?.let {
                    Log.i("TAG", "Select clicked at latitude = ${it.latitude}, longitude = ${it.longitude}")
                    viewModel.writeLatitude((it.latitude).toString())
                    viewModel.writeLongitude((it.longitude).toString())
                    action.invoke(NavigationRoute.Settings, false)
                }
            }
        ) {
            Text("Select", fontSize = 24.sp)
        }
    }
}
