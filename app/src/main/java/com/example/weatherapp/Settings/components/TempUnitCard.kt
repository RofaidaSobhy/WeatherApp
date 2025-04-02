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
import com.example.weatherapp.Settings.Constants.TempUnit

@Preview(showSystemUi = false)
@Composable
fun TempUnitCard() {
    val selectedTempUnit = remember { mutableStateOf("") }
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
                        .background(Color(0xFFF33561))
                ) {
                    Image(
                        painter = painterResource(R.drawable.tempreture),
                        contentDescription = "Temp Unit Icon",
                        Modifier.clip(CircleShape)
                            .size(50.dp)

                    )

                }

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.temp_unit_label),
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
                    selected = selectedTempUnit.value == TempUnit.celsius,
                    onClick = { selectedTempUnit.value = TempUnit.celsius },
                    colors = RadioButtonDefaults.colors(Color(0xFF379DF1))
                )
                //Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = TempUnit.celsius,
                    color = Color.White,
                    fontSize = 16.sp,

                    )
                //Spacer(modifier = Modifier.width(4.dp))

                RadioButton(
                    selected = selectedTempUnit.value == TempUnit.kelvin,
                    onClick = { selectedTempUnit.value = TempUnit.kelvin },
                    colors = RadioButtonDefaults.colors(Color(0xFF379DF1))
                )
                //Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = TempUnit.kelvin,
                    color = Color.White,
                    fontSize = 16.sp
                )
                // Spacer(modifier = Modifier.width(4.dp))
                RadioButton(
                    selected = selectedTempUnit.value == TempUnit.fahrenheit,
                    onClick = { selectedTempUnit.value = TempUnit.fahrenheit },
                    colors = RadioButtonDefaults.colors(Color(0xFF379DF1))
                )
                //Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = TempUnit.fahrenheit,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }

}