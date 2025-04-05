package com.example.weatherapp.favorite.components

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
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R

@Composable
fun FAB(action : ()-> Unit) {
    Box (modifier = Modifier.fillMaxSize()){
        FloatingActionButton(

            modifier = Modifier
                .padding(16.dp)
                .padding(vertical = 150.dp)
                .align(Alignment.BottomEnd)
                .clip(CircleShape)
                .shadow(elevation = 4.dp)

            ,containerColor = Color(0xFF222A36)



            ,onClick = {action()}
        ) {

            Image(
                painter = painterResource(R.drawable.favorite),
                contentDescription = "favorite Image",
                Modifier.size(30.dp)
            )

        }
    }
}