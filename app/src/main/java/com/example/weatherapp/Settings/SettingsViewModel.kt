package com.example.weatherapp.Settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repo.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel (private val repo : WeatherRepository) : ViewModel() {
    private val mutableTempUnit=  MutableStateFlow("")
    val tempUnit = mutableTempUnit.asStateFlow()


    fun readTempUnit(){
        viewModelScope.launch (Dispatchers.IO) {
            val result = repo.readTempUnit()
            result.collect{
                mutableTempUnit.value=it
                Log.i("TAG", "readTempUnit: $it ")
                Log.i("TAG", "readTempUnit: tempUnit = ${tempUnit.value}")
            }
        }
    }

    fun writeTempUnit(tempUnit:String){
        viewModelScope.launch (Dispatchers.IO) {
            mutableTempUnit.value = tempUnit
            repo.writeTempUnit(tempUnit)
        }
    }

}

class SettingsFactory(private val repo: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return SettingsViewModel(repo) as T
    }
}