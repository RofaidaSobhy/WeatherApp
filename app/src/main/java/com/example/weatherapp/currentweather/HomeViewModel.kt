package com.example.weatherapp.currentweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.utils.Constants.API_KEY
import com.example.weatherapp.data.models.Response.Response
import com.example.weatherapp.data.repo.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (private val repo : WeatherRepository) : ViewModel() {
    private val mutableCurrentWeather=  MutableStateFlow<Response>(Response.Loading)
    val currentWeather= mutableCurrentWeather.asStateFlow()

    private val mutableWeatherForecast=  MutableStateFlow<Response>(Response.Loading)
    val weatherForecast= mutableWeatherForecast.asStateFlow()

    private val mutableMessage= MutableSharedFlow<String>()
    val message = mutableMessage.asSharedFlow()

    fun getCurrentWeather(latitude:Double, longitude:Double, apiKey: String = API_KEY, units: String = "metric", language: String = "en"){
        viewModelScope.launch (Dispatchers.IO) {
            try {

                val result = repo.getCurrentWeather(latitude, longitude, apiKey, units, language)
                if (result != null) {
                    result
                        .catch { ex ->
                            mutableCurrentWeather.value = Response.Failure(ex)
                            mutableMessage.emit("Error From API: ${ex.message}")

                        }
                        .collect {
                            mutableCurrentWeather.value = Response.Success(it)
                        }

                } else {
                    mutableMessage.emit("Please try again later")
                }

            } catch (ex: Exception) {
                mutableCurrentWeather.value = Response.Failure(ex)
                mutableMessage.emit("An error occurred, ${ex.message} ")
            }
        }

    }


    fun getWeatherForecast(latitude:Double, longitude:Double, apiKey: String = API_KEY, units: String = "metric", language: String = "en"){
        viewModelScope.launch (Dispatchers.IO) {
            try {

                val result = repo.getWeatherForecast(latitude, longitude, apiKey, units, language)
                if (result != null) {
                    result
                        .catch { ex ->
                            mutableWeatherForecast.value = Response.Failure(ex)
                            mutableMessage.emit("Error From API: ${ex.message}")

                        }
                        .collect {
                            mutableWeatherForecast.value = Response.Success(it)
                        }

                } else {
                    mutableMessage.emit("Please try again later")
                }

            } catch (ex: Exception) {
                mutableWeatherForecast.value = Response.Failure(ex)
                mutableMessage.emit("An error occurred, ${ex.message} ")
            }
        }

    }

}

class HomeFactory(private val repo: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return HomeViewModel(repo) as T
    }
}