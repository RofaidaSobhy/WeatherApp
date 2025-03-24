package com.example.weatherapp.currentweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.models.Weather
import com.example.weatherapp.data.repo.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrentWeatherViewModel (private val repo : WeatherRepository) : ViewModel() {
    private val mutableCurrentWeather: MutableLiveData<List<Weather>> = MutableLiveData<List<Weather>>()
    val weather : LiveData<List<Weather>> = mutableCurrentWeather

    private val mutableMessage: MutableLiveData<String> = MutableLiveData<String>()
    val message: LiveData<String> = mutableMessage

    fun getCurrentWeather(latitude:String, longitude:String){
        try{
            viewModelScope.launch (Dispatchers.IO){
                val result = repo.getCurrentWeather(latitude,longitude)
                if(result != null){
                    result
                        .collect{
                            mutableCurrentWeather.postValue(it)
                        }

                }else{
                    mutableMessage.postValue("Please try again later")
                }
            }
        }catch (ex:Exception){
            mutableMessage.postValue("An error occurred, ${ex.message} ")
        }

    }

}

