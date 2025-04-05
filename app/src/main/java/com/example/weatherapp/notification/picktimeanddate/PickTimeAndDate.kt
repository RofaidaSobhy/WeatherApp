package com.example.weatherapp.notification.picktimeanddate

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.navigation.NavigationRoute
import com.example.weatherapp.notification.NotificationViewModel
import com.example.weatherapp.notification.components.DatePicker
import com.example.weatherapp.notification.components.TimePicker

@RequiresApi(Build.VERSION_CODES.O)

@Composable
fun PickTimeAndDateView(viewModel: NotificationViewModel , back: (NavigationRoute, Boolean)->Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
        .background(Color(0xFF1B1D1E)),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DatePicker()
        Spacer(Modifier.height(16.dp))
        Row{
            TimePicker("Start")
            Spacer(modifier = Modifier.width(16.dp))
            TimePicker("End")

        }

        Spacer(Modifier.height(16.dp))
        Row{
            Button(

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF222A36),
                    contentColor = Color(0xFFFFFFFF),

                ),
                onClick = {back.invoke(NavigationRoute.Notification, false)}
            ) {
                Text(text="Cancel", fontSize = 24.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF222A36),
                contentColor = Color(0xFFFFFFFF),

                ),
                onClick = {back.invoke(NavigationRoute.Notification, false)}
            ){
                Text("Save", fontSize = 24.sp)
            }
        }
    }
}