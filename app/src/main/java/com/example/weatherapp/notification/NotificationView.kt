package com.example.weatherapp.notification

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.notification.components.DatePicker

import com.example.weatherapp.notification.components.NotificationFAB
import com.example.weatherapp.notification.components.TimePicker
import com.example.weatherapp.utils.Constants.SCREEN_PADDING

//@Preview(showSystemUi = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationView(viewModel: NotificationViewModel , action: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1B1D1E))
            .padding(SCREEN_PADDING),
        verticalArrangement = Arrangement.Top,
    ) {
        /*LazyColumn {
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
        }*/
        NotificationFAB(action)
    }
}