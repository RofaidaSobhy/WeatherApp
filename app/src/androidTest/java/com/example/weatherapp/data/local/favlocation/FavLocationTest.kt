package com.example.weatherapp.data.local.favlocation

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.weatherapp.data.models.FavLocation
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class FavLocationTest {
    private lateinit var dao: FavLocationDao
    private lateinit var database: FavLocationDatabase

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavLocationDatabase::class.java
        ).build()

        dao = database.getFavLocationDao()
    }

    @After
    fun dearDown()= database.close()


    @Test
    fun insertFavLocation_insertedSameFavLocationTwice_Minus1() = runTest{
        //Given declare new favLocation
        val favLocation1 = FavLocation(0.0,0.0,"county1","city1")
         dao.insertFavLocation(favLocation1)
        val result = dao.insertFavLocation(favLocation1)

        assertThat(result,`is`(-1L))

    }

    @Test
    fun deleteFavLocation_deletedFavLocation_1() = runTest{
        //Given declare new favLocation
        val favLocation1 = FavLocation(0.0,0.0,"county1","city1")
        dao.insertFavLocation(favLocation1)
        val result = dao.deleteFavLocation(favLocation1)

        assertNotNull(result)
        assertThat(result,`is`(1))

    }
}



