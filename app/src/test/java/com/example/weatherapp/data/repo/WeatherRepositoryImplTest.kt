package com.example.weatherapp.data.repo

import com.example.weatherapp.data.local.LocalDataSource
import com.example.weatherapp.data.local.StubLocalDataSource
import com.example.weatherapp.data.models.FavLocation
import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class WeatherRepositoryImplTest {

    lateinit var repo: WeatherRepository
    lateinit var localDataSource: LocalDataSource
    lateinit var remoteDataSource: WeatherRemoteDataSource

    private val localData = MutableStateFlow(
        listOf(
            FavLocation(0.0, 0.0, "country1", "city1"),
            FavLocation(0.0, 0.0, "country2", "city2")
        )
    )

    @Before
    fun setUp() {
        localDataSource = mockk(relaxed = true)
        remoteDataSource = mockk(relaxed = true)

        coEvery { localDataSource.getFavLocations() } returns localData

        repo = WeatherRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `test getFavLocations returns the correct data`() = runTest {
        val result = repo.getFavLocations()

        result.collect { favLocations ->
            assertEquals(favLocations?.size ,2)
            assertEquals(favLocations?.get(0)?.city , "city1")
            assertEquals(favLocations?.get(1)?.country , "country2")
        }


        coVerify { localDataSource.getFavLocations() }
    }

    @Test
    fun `test addFavLocation adds a location successfully`() = runTest {
        val newLocation = FavLocation(1.0, 1.0, "country3", "city3")

        coEvery { localDataSource.insertFavLocation(newLocation) } returns 1L
        val rowId = repo.addFavLocation(newLocation)

        /*val updatedLocalData = localData.value.toMutableList().apply {
            add(newLocation)
        }

        localData.value = updatedLocalData
        assertEquals(localData.value.size, 3)*/

        assertEquals(rowId, 1L)

        @Test
        fun `test deleteFavLocation removes a location successfully`() = runTest {
            val locationToDelete = FavLocation(0.0, 0.0, "country1", "city1")

            coEvery { localDataSource.deleteFavLocation(locationToDelete) } returns 1

            val deleteResult = repo.removeFavLocation(locationToDelete)

            /* val updatedLocalData = localData.value.toMutableList().apply {
                remove(locationToDelete)
            }

            localData.value = updatedLocalData
            assertEquals(localData.value.size ,1)*/

            assertEquals(deleteResult, 1)
        }

    }
}