package com.example.weatherapp.Settings.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.Settings.Constants.WindSpeedUnit

@Preview(showSystemUi = false)
@Composable
fun WindSpeedUnitCard() {
    val selectedWindSpeedUnit = remember { mutableStateOf("") }
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
                        .background(Color(0xFF9B3AAD))
                ) {
                    Image(
                        painter = painterResource(R.drawable.wind),
                        contentDescription = "WindSpeedUnit Icon",
                        modifier = Modifier.size(40.dp)
                            .align(alignment = Alignment.Center)



                    )

                }

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.wind_speed_unit_label),
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
                    selected = selectedWindSpeedUnit.value == WindSpeedUnit.meter,
                    onClick = { selectedWindSpeedUnit.value = WindSpeedUnit.meter },
                    colors = RadioButtonDefaults.colors(Color(0xFF379DF1))
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = WindSpeedUnit.meter,
                    color = Color.White,
                    fontSize = 18.sp,

                    )
                Spacer(modifier = Modifier.width(24.dp))

                RadioButton(
                    selected = selectedWindSpeedUnit.value == WindSpeedUnit.mile,
                    onClick = { selectedWindSpeedUnit.value = WindSpeedUnit.mile },
                    colors = RadioButtonDefaults.colors(Color(0xFF379DF1))
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = WindSpeedUnit.mile,
                    color = Color.White,
                    fontSize = 16.sp
                )

            }
        }
    }

}