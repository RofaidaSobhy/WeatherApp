package com.example.weatherapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.repo.WeatherRepository

class FavoriteViewModel(private val repo : WeatherRepository):ViewModel(){

}

class FavoriteFactory(private val repo: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return FavoriteViewModel(repo) as T
    }
}