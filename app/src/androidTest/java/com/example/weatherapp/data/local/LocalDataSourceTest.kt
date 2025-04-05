package com.example.weatherapp.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import com.example.weatherapp.data.local.favlocation.FavLocationDao
import com.example.weatherapp.data.local.favlocation.FavLocationDatabase
import com.example.weatherapp.data.local.settings.DummySettingsDao
import com.example.weatherapp.data.local.settings.SettingsDao
import com.example.weatherapp.data.models.FavLocation
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest {

    private lateinit var settingsDao: SettingsDao
    private lateinit var favLocationDao: FavLocationDao
    private lateinit var database: FavLocationDatabase
    private lateinit var localDataSource: LocalDataSourceImpl

    @Before
    fun setUp(){
        settingsDao = DummySettingsDao()
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavLocationDatabase::class.java
        ).build()

        favLocationDao = database.getFavLocationDao()
        localDataSource=LocalDataSourceImpl(settingsDao,favLocationDao)
    }

    @After
    fun dearDown()= database.close()


    @Test
    fun insertFavLocation_insertedSameFavLocationTwice_Minus1() = runTest{
        val favLocation1 = FavLocation(0.0,0.0,"country1","city1")
        val result = localDataSource.insertFavLocation(favLocation1)

        assertThat(result, `is`(1L))
        val result2 = favLocationDao.getAllFavoriteLocations().first()

        assertTrue(result2.isNotEmpty())
        assertEquals(result2.first().country, "country1")
    }

    @Test
    fun deleteFavLocation_deletedFavLocation_1() = runTest{
        val favLocation1 = FavLocation(0.0,0.0,"county1","city1")
        localDataSource.insertFavLocation(favLocation1)
        val result = localDataSource.deleteFavLocation(favLocation1)

        assertNotNull(result)
        assertThat(result, `is`(1))

        val result2 = favLocationDao.getAllFavoriteLocations().first()

        assertTrue(result2.isEmpty())

    }
}




