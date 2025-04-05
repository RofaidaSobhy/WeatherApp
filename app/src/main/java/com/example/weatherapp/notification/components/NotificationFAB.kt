package com.example.weatherapp.notification.components

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationManagerCompat
import com.example.weatherapp.R
import android.provider.Settings
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext


@Composable
fun NotificationFAB(action: () -> Unit) {
    val context = LocalContext.current
    Box (modifier = Modifier.fillMaxSize()){
        FloatingActionButton(

            modifier = Modifier
                .padding(16.dp)
                .padding(vertical = 150.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .shadow(elevation = 4.dp)

            ,containerColor = Color(0xFF222A36)



            ,onClick = {

                /*if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
                    // Check if the app has "Draw over other apps" permission
                    if (!Settings.canDrawOverlays(context)) {
                        // If not, navigate to "Display over other apps" settings page
                        openOverlaySettings(context)
                    } else {
                        // If permission is granted, proceed with your task
                        Toast.makeText(context, "You have the permission", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // If notification permission is not granted
                    openNotificationSettings(context)

                    Toast.makeText(context, "Please enable notifications", Toast.LENGTH_SHORT).show()
                }*/

                action()

            }
        ) {

            Image(
                painter = painterResource(R.drawable.notification),
                contentDescription = "favorite Image",
                Modifier.size(30.dp)
            )

        }
    }
}

fun openOverlaySettings(context: Context) {
    // Intent to open the "Display over other apps" settings page for this app specifically
    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
    context.startActivity(intent)
}

fun openNotificationSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
        putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
    }
    context.startActivity(intent)
}