package com.example.weatherapp.data.local.favlocation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapp.data.models.FavLocation

@Database(entities = [FavLocation::class] , version = 1)
abstract class FavLocationDatabase : RoomDatabase() {
    abstract fun getFavLocationDao(): FavLocationDao

    companion object{
        @Volatile
        private var instanceOfDB : FavLocationDatabase? = null
        fun getInstance(context: Context): FavLocationDatabase{
            return instanceOfDB ?: synchronized(this){
                val temp: FavLocationDatabase = Room.databaseBuilder(context,FavLocationDatabase::class.java,"favlocationdb").build()
                instanceOfDB = temp
                temp
            }

        }
    }
}