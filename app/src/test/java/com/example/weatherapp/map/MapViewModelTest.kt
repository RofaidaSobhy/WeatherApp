package com.example.weatherapp.map

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapp.data.repo.WeatherRepositoryImpl
import com.google.android.gms.maps.model.LatLng
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MapViewModelTest{
    lateinit var repo:WeatherRepositoryImpl
    private lateinit var viewModel: MapViewModel

    @Before
    fun setUp(){
        repo = mockk(relaxed = true)
        viewModel=MapViewModel(repo)
    }


    @Test
    fun setSelectedLocation_setLocation_sameLocation(){
        val location = LatLng(1.0,1.0)

        viewModel.setSelectedLocation(location)

        val result = viewModel.selectedLocation.value
        assertEquals(result, location)
    }



}



