package com.example.weatherapp.data.local.favlocation

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.data.models.FavLocation
import kotlinx.coroutines.flow.Flow

@Dao
interface FavLocationDao {
    @Query("SELECT * FROM favlocation")
    fun getAllFavoriteLocations(): Flow<List<FavLocation>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavLocation(favLocation: FavLocation): Long

    @Delete
    suspend fun deleteFavLocation(favLocation: FavLocation): Int
}