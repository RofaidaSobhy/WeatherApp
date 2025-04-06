package com.example.weatherapp.data.repo

import com.example.weatherapp.data.local.FakeLocalDataSource
import com.example.weatherapp.data.models.FavLocation
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class WeatherRepositoryImplTest {

    lateinit var repo: WeatherRepository
    lateinit var localDataSource: FakeLocalDataSource
    lateinit var remoteDataSource: WeatherRemoteDataSource

    private val localData = mutableListOf(

            FavLocation(0.0, 0.0, "country1", "city1"),
            FavLocation(0.0, 0.0, "country2", "city2")

    )

    @Before
    fun setUp() {
        remoteDataSource = mockk(relaxed = true)

        localDataSource = FakeLocalDataSource(localData)

        repo = WeatherRepositoryImpl(remoteDataSource, localDataSource)
    }



    @Test
    fun `test addFavLocation adds a location successfully`() = runTest {
        val newLocation = FavLocation(1.0, 1.0, "country3", "city3")

        val rowId = repo.addFavLocation(newLocation)



        assertEquals(rowId, 1L)

    }
    @Test
    fun `test deleteFavLocation removes a location successfully`() = runTest {
        val locationToDelete = FavLocation(0.0, 0.0, "country1", "city1")

        val deleteResult = repo.removeFavLocation(locationToDelete)


        assertEquals(deleteResult, 1)
    }
}


