package com.example.weatherapp.currentweather.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.weatherapp.utils.DateTimeUtils
import com.example.weatherapp.utils.WeatherUtils
import java.util.Locale


@OptIn(ExperimentalGlideComposeApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainWeatherCard(
    weatherId:Int,
    weatherIcon:String,
    dt: Int,
    timeZone:Int,
    currentTemp: Int,
    tempUnit:String,
    feelsLikeTemp: Int,
    weatherDescription: String,
    location: String

) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp / 1.2f
    val date = DateTimeUtils.getDate(dt,timeZone)
    val time = DateTimeUtils.getTime(dt,timeZone)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenWidth),

        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(
                alpha = 0.5f
            ),
        ),


    ){
        Box{
            val weatherImage = WeatherUtils.getWeatherImage(weatherId)
            Image(
                painter = painterResource(weatherImage),
                contentDescription = "Weather Image",
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box (modifier = Modifier
                .background(Color.Black.copy(alpha = 0.6f))
                .fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Log.i("TAG", "MainWeatherCard: Icon: $weatherIcon")

                Text(
                    text = date,
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Normal
                )


                Text(
                    text = time,
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(48.dp))

                Box(
                    modifier = Modifier.align(Alignment.End)
                ) {

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Row(){
                            GlideImage(
                                model = "https://openweathermap.org/img/wn/${weatherIcon}@2x.png",
                                contentDescription = "Weather icon",
                                modifier = Modifier
                                    .size(70.dp)

                            )

                            Text(
                                text = "$currentTemp°$tempUnit",
                                color = Color.White,
                                style = MaterialTheme.typography.displayLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Feels like $feelsLikeTemp°$tempUnit",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = weatherDescription.uppercase(Locale.getDefault()),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.weight(1.0f))
                        Text(
                            text = location,
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun MainWeatherCardPreview() {
    MainWeatherCard(
        weatherIcon = "",
        weatherId = 800,
        dt = 1743173298,
        timeZone = 7500,
        currentTemp = 22,
        tempUnit = "C",
        feelsLikeTemp = 0,
        weatherDescription = "cloudy",
        location = "Egypt",

    )
}