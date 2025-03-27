package com.example.weatherapp.currentweather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.weatherapp.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherItemCard(
    icon: String,
    title: String,
    value: String
) {
    val color1 = Color(0xFF4776E6)
    val color2 = Color(0xFF8E54E9)

    val gradient = Brush.verticalGradient(
        colors = listOf(color1, color2)
    )

    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier.widthIn(max = 150.dp).heightIn(max = 140.dp)
    ) {
        Box(
            modifier = Modifier
                .background(brush = gradient)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {

                GlideImage(
                    model = icon,
                    contentDescription = title,
                    modifier = Modifier
                        .size(25.dp)
                        .graphicsLayer(alpha = 1f)
                        .drawWithContent {
                            drawContent()
                            drawRect(
                                color = Color.White,
                                blendMode = BlendMode.SrcIn
                            )
                        }
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = value,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherItemCardPreview() {
    WeatherItemCard(
        icon = "",
        title = "Wind",
        value = "5 km/h"
    )
}