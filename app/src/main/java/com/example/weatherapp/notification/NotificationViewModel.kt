package com.example.weatherapp.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.repo.WeatherRepository

class NotificationViewModel(private val repo : WeatherRepository): ViewModel(){
}

class NotificationFactory(private val repo: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return NotificationViewModel(repo) as T
    }
}