package com.example.weatherapp.notification

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.repo.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationViewModel(private val repo : WeatherRepository): ViewModel(){

    private val mutableStartTime=  MutableStateFlow(0L)
    val startTime = mutableStartTime.asStateFlow()

    fun setStartTime(startTime:Long){
        mutableStartTime.value=startTime
        Log.i("TAG", "setStartTime: startTime = $startTime")
    }

}

class NotificationFactory(private val repo: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return NotificationViewModel(repo) as T
    }
}