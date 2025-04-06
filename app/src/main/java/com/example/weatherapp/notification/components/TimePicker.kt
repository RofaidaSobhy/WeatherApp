package com.example.weatherapp.notification.components

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.weatherapp.notification.NotificationViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimePicker(timeName:String,viewModel: NotificationViewModel) {
    val context = LocalContext.current

    val calendar = remember { Calendar.getInstance() }
    var pickedTime by remember {
        mutableStateOf(LocalTime.now())
    }

    val formattedTime by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("hh:mm a")
                .format(pickedTime)
        }
    }

    calendar.set(Calendar.HOUR_OF_DAY, pickedTime.hour)
    calendar.set(Calendar.MINUTE, pickedTime.minute)
    calendar.set(Calendar.SECOND, pickedTime.second)


    val timeDialogState = rememberMaterialDialogState()

    Column {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF222A36),
                contentColor = Color(0xFFFFFFFF),

                ),
            onClick = {
                timeDialogState.show()
            }) {
            Text(text = timeName, fontSize = 24.sp, color = Color.White)
        }
        Text(text = formattedTime, fontSize = 24.sp, color = Color.White)

    }
    MaterialDialog(
        dialogState = timeDialogState,
        buttons = {
            positiveButton(text = "Ok") {
                Toast.makeText(
                    context,
                    "Clicked ok",
                    Toast.LENGTH_LONG
                ).show()

                if(timeName == "Start"){
                    viewModel.setStartTime(calendar.timeInMillis)
                }
            }
            negativeButton(text = "Cancel")
        }
    ) {
        timepicker(
            initialTime = LocalTime.now(),
            title = "Pick a time",
        ) {
            pickedTime = it
        }
    }
}

