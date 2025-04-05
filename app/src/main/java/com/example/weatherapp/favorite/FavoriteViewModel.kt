package com.example.weatherapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.models.FavLocation
import com.example.weatherapp.data.models.Response.Response
import com.example.weatherapp.data.repo.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repo : WeatherRepository):ViewModel(){
    private val mutableFavLocations = MutableStateFlow<Response>(Response.Loading)
    val favLocations = mutableFavLocations.asStateFlow()

    private val mutableMessage= MutableSharedFlow<String>()
    val message = mutableMessage.asSharedFlow()

    fun getFavLocations(){
        viewModelScope.launch (Dispatchers.IO) {

            try {
                val result = repo.getFavLocations()
                if (result != null) {
                    result
                        .catch { ex ->
                            mutableFavLocations.value = Response.Failure(ex)
                            mutableMessage.emit("Error From Local: ${ex.message}")
                        }
                        .collect {
                            mutableFavLocations.value = Response.Success(it)
                        }

                } else {
                    mutableMessage.emit("Please try again later")
                }

            } catch (ex: Exception) {
                mutableFavLocations.value = Response.Failure(ex)
                mutableMessage.emit("An error occurred, ${ex.message} ")
            }
        }
    }

    fun removeFromFavorites(favLocation: FavLocation?){
        viewModelScope.launch (Dispatchers.IO) {

            if (favLocation != null) {
                try {
                    val result = repo.removeFavLocation(favLocation)
                    if (result > 0) {
                        mutableMessage.emit("remove Location Done Successfully")
                    } else {
                        mutableMessage.emit("Location is not exist in Favorites!")
                    }
                } catch (ex: Exception) {
                    mutableMessage.emit("Couldn't remove Location, ${ex.message}")
                }

            } else {
                mutableMessage.emit("Couldn't remove Location, Missing data")
            }
        }

    }

    fun addToFavorites(favLocation: FavLocation?){
        viewModelScope.launch (Dispatchers.IO) {
            if (favLocation != null) {

                try {
                    val result = repo.addFavLocation(favLocation)
                    if (result > 0) {
                        mutableMessage.emit("Added Successfully!")
                    } else {
                        mutableMessage.emit("Location is Already in Favorites!")
                    }
                } catch (ex: Exception) {
                    mutableMessage.emit("Couldn't Add Location, ${ex.message}")
                }

            } else {
                mutableMessage.emit("Couldn't Add Location, Missing data")
            }
        }
    }
}

class FavoriteFactory(private val repo: WeatherRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T{
        return FavoriteViewModel(repo) as T
    }
}