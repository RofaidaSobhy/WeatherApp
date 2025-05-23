package com.example.weatherapp.settings.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.settings.Constants.Location
import com.example.weatherapp.settings.SettingsViewModel

//@Preview(showSystemUi = false)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LocationCard(action : ()-> Unit, viewModel: SettingsViewModel) {

    viewModel.readLocationMethod()
    val savedLocationMethod= viewModel.locationMethod.value

    val selectedLocation = remember { mutableStateOf("") }
    selectedLocation.value = savedLocationMethod

    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF222A36)
        )
    ) {
        Column {
            Row(
                Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFF9900))
                ) {
                    Image(
                        painter = painterResource(R.drawable.location),
                        contentDescription = "Location Icon",
                        modifier = Modifier.size(40.dp)
                            .align(alignment = Alignment.Center)



                    )

                }

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.location_label),
                    color = Color.White,
                    fontSize = 24.sp

                )
            }





            Row(
                Modifier.padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically

            )
            {

                RadioButton(
                    selected = selectedLocation.value == Location.gps,
                    onClick = {
                        selectedLocation.value = Location.gps
                        viewModel.writeLocationMethod(Location.gps)

                    },
                    colors = RadioButtonDefaults.colors(Color(0xFF379DF1))
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = Location.gps,
                    color = Color.White,
                    fontSize = 18.sp,

                    )
                Spacer(modifier = Modifier.width(24.dp))
                RadioButton(
                    selected = selectedLocation.value == Location.map,
                    onClick = {
                        selectedLocation.value = Location.map
                        viewModel.writeLocationMethod(Location.map)

                        action()
                    },
                    colors = RadioButtonDefaults.colors(Color(0xFF379DF1))
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = Location.map,
                    color = Color.White,
                    fontSize = 16.sp
                )

            }
        }
    }

}