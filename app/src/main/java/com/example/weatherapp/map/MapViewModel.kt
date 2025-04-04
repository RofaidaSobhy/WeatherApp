package com.example.weatherapp.map

import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repo.WeatherRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import timber.log.Timber

class MapViewModel (private val repo : WeatherRepository) : ViewModel() {

    private val mutableUserLocation = mutableStateOf<LatLng?>(null)
    val userLocation: State<LatLng?> = mutableUserLocation

    private val mutableSelectedLocation = mutableStateOf<LatLng?>(null)
    var selectedLocation: State<LatLng?> = mutableSelectedLocation

    /*private val mutableLatitude=  MutableStateFlow("")
    val latitude = mutableLatitude.asStateFlow()


    private val mutableLongitude=  MutableStateFlow("")
    val longitude = mutableLongitude.asStateFlow()*/

    // Function to fetch the user's location and update the state
    fun fetchUserLocation(context: Context, fusedLocationClient: FusedLocationProviderClient) {
        // Check if the location permission is granted
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            try {
                // Fetch the last known location
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        // Update the user's location in the state
                        val userLatLng = LatLng(it.latitude, it.longitude)
                        mutableUserLocation.value = userLatLng
                    }
                }
            } catch (e: SecurityException) {
                Timber.e("Permission for location access was revoked: ${e.localizedMessage}")
            }
        } else {
            Timber.e("Location permission is not granted.")
        }
    }

    // Function to geocode the selected place and update the selected location state
    fun selectLocation(selectedPlace: String, context: Context) {
        viewModelScope.launch {
            val geocoder = Geocoder(context)
            val addresses = withContext(Dispatchers.IO) {
                // Perform geocoding on a background thread
                geocoder.getFromLocationName(selectedPlace, 1)
            }
            if (!addresses.isNullOrEmpty()) {
                // Update the selected location in the state
                val address = addresses[0]
                val latLng = LatLng(address.latitude, address.longitude)
                mutableSelectedLocation.value = latLng
            } else {
                Timber.tag("MapScreen").e("No location found for the selected place.")
            }
        }
    }

    fun setUserLocation(latLng: LatLng) {
        mutableUserLocation.value = latLng
    }
    fun setSelectedLocation(latLng: LatLng) {
        mutableSelectedLocation.value = latLng
    }

    fun writeLatitude(latitude:String){
        viewModelScope.launch (Dispatchers.IO) {
            //mutableLatitude.value = latitude
            repo.writeLatitude(latitude)
        }
    }

    fun writeLongitude(longitude:String){
        viewModelScope.launch (Dispatchers.IO) {
            //mutableLongitude.value = longitude
            repo.writeLongitude(longitude)
        }
    }

}
class MapFactory(private val repo: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return MapViewModel(repo) as T
    }
}