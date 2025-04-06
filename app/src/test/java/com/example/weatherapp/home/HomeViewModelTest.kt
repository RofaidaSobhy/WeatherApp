package com.example.weatherapp.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapp.data.repo.WeatherRepositoryImpl
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest{

    lateinit var repo: WeatherRepositoryImpl
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp(){
        repo = mockk(relaxed = true)
        viewModel= HomeViewModel(repo)
    }


    @Test
    fun getCurrentWeather_requaredData_itsWeather(){


        val result = viewModel.getCurrentWeather(1.0,1.0)

        assertNotNull(viewModel.currentWeather)
    }



}
