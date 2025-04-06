package com.example.weatherapp.settings

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

    private val mutableWindSpeedUnit=  MutableStateFlow("")
    val windSpeedUnit = mutableWindSpeedUnit.asStateFlow()

    private val mutableLocationMethod=  MutableStateFlow("")
    val locationMethod = mutableLocationMethod.asStateFlow()

    private val mutableLanguage=  MutableStateFlow("")
    val language = mutableLanguage.asStateFlow()


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

    fun readWindSpeedUnit(){
        viewModelScope.launch (Dispatchers.IO) {
            val result = repo.readWindSpeedUnit()
            result.collect{
                mutableWindSpeedUnit.value=it
            }
        }
    }

    fun writeWindSpeedUnit(windSpeedUnit:String){
        viewModelScope.launch (Dispatchers.IO) {
            mutableWindSpeedUnit.value = windSpeedUnit
            repo.writeWindSpeedUnit(windSpeedUnit)
        }
    }

    fun readLocationMethod(){
        viewModelScope.launch (Dispatchers.IO) {
            val result = repo.readLocationMethod()
            result.collect{
                mutableLocationMethod.value=it
            }
        }
    }

    fun writeLocationMethod(locationMethod:String){
        viewModelScope.launch (Dispatchers.IO) {
            mutableLocationMethod.value = locationMethod
            repo.writeLocationMethod(locationMethod)
        }
    }



    fun readLanguage(){
        viewModelScope.launch (Dispatchers.IO) {
            val result = repo.readLanguage()
            result.collect{
                mutableLanguage.value=it
            }
        }
    }

    fun writeLanguage(language:String){
        viewModelScope.launch (Dispatchers.IO) {
            mutableLanguage.value = language
            repo.writeLanguage(language)
        }
    }







}

class SettingsFactory(private val repo: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return SettingsViewModel(repo) as T
    }
}