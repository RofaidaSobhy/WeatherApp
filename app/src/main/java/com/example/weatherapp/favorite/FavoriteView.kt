package com.example.weatherapp.favorite

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.weatherapp.data.models.FavLocation
import com.example.weatherapp.data.models.Response.Response
import com.example.weatherapp.favorite.components.FavLocationRow
import com.example.weatherapp.favorite.components.FavoriteFAB
import com.example.weatherapp.home.LoadingIndicator

import com.example.weatherapp.utils.Constants.SCREEN_PADDING
import kotlinx.coroutines.flow.filter

@Composable
fun FavoriteView(viewModel: FavoriteViewModel, action: () -> Unit, action2: (Double,Double) -> Unit) {
    viewModel.getFavLocations()
    val favLocationsState by viewModel.favLocations.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    var lastRemovedLocation by remember { mutableStateOf<FavLocation?>(null) }

    val snackbarResult = remember { mutableStateOf<SnackbarResult?>(null) }

    LaunchedEffect(viewModel.message) {
        viewModel.message
            .filter { message -> message.isNotBlank() }
            .collect { message ->
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short,
                    actionLabel = "Undo"
                )
            }
    }

    LaunchedEffect(snackbarResult.value) {
        snackbarResult.value?.let {
            when (it) {
                SnackbarResult.ActionPerformed -> {
                    lastRemovedLocation?.let { location ->
                        Log.i("TAG", "FavoriteView: Location: ${location.country}")
                        viewModel.addToFavorites(location)
                    }
                }
                else -> {
                    Log.i("TAG", "FavoriteView: Undo not performed")
                }
            }
        }
    }

    when (favLocationsState) {
        is Response.Loading -> {
            LoadingIndicator()
        }
        is Response.Success<*> -> {
            Scaffold(
                snackbarHost = {
                    SnackbarHost(snackBarHostState, Modifier.padding(vertical = 100.dp))
                }
            ) { contentPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFF1B1D1E))
                        .padding(SCREEN_PADDING),
                    verticalArrangement = Arrangement.Top,
                ) {
                    LazyColumn {
                        items(
                            (favLocationsState as Response.Success<List<FavLocation>>).data?.size
                                ?: 0
                        ) { it: Int ->
                            val location =
                                (favLocationsState as Response.Success<List<FavLocation>>).data?.get(
                                    it
                                )

                            FavLocationRow(location, {
                                location?.let {
                                    lastRemovedLocation = it
                                    viewModel.removeFromFavorites(it)
                                }
                            },
                                action2
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                    FavoriteFAB(action)
                }
            }
        }
        is Response.Failure -> {
            Text(
                text = "Sorry, we can't show Favorite Locations now",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(),
                fontSize = 22.sp
            )
        }
    }
}

