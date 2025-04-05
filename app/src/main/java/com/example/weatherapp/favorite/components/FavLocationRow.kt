package com.example.weatherapp.favorite.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.weatherapp.R
import com.example.weatherapp.data.models.FavLocation

//@Preview
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FavLocationRow(favLocation: FavLocation?, action: () -> Unit, action2: (Double, Double) -> Unit) {

    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF222A36).copy(alpha = 0.2f)

        )
        , border = BorderStroke(0.5.dp,Color.White.copy(alpha = 0.2f))
        , onClick ={
            Log.i("TAG", "FavLocationRow: fav: latitue= ${favLocation?.latitude ?: 0.0}, longitue = ${favLocation?.longitude ?: 0.0} ")
            action2.invoke(favLocation?.latitude ?: 0.0,favLocation?.longitude ?: 0.0)

        }
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically

        ){
            if(favLocation!=null) {
                Text(
                    text = favLocation.country,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(20.dp)

                )

                Text(
                    text = favLocation.city,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "delete",
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { action() },
                        tint = Color.White
                        ,

                    )
                }

            }

        }

    }

}

