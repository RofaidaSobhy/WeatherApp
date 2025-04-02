package com.example.weatherapp.Settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.repo.WeatherRepository

class SettingsViewModel (private val repo : WeatherRepository) : ViewModel() {

}

class SettingsFactory(private val repo: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return SettingsViewModel(repo) as T
    }
}