package com.example.weatherapp.settings.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import com.example.weatherapp.settings.Constants.Language
import com.example.weatherapp.settings.Constants.Location
import com.example.weatherapp.settings.SettingsViewModel

//@Preview(showSystemUi = false)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LanguageCard(viewModel: SettingsViewModel) {

    viewModel.readLanguage()
    val savedLanguage= viewModel.language.value

    val selectedLanguage = remember { mutableStateOf("") }
    selectedLanguage.value = savedLanguage
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
                verticalAlignment = Alignment.CenterVertically,

                ){

                Image(
                    painter = painterResource(R.drawable.language),
                    contentDescription = "Language Image",
                    Modifier.clip(CircleShape)
                        .size(50.dp)

                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.language_label),
                    color = Color.White,
                    fontSize = 24.sp

                )

            }



            Row (
                Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically)
            {

                RadioButton(
                    selected = selectedLanguage.value == Language.arabic,
                    onClick = {
                        selectedLanguage.value = Language.arabic
                        viewModel.writeLanguage(Language.arabic)

                    },
                    colors = RadioButtonDefaults.colors(Color(0xFF379DF1))
                )
                //Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = Language.arabic,
                    color = Color.White,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(16.dp))

                RadioButton(
                    selected = selectedLanguage.value == Language.english,
                    onClick = {
                        selectedLanguage.value = Language.english
                        viewModel.writeLanguage(Language.english)

                              },
                    colors = RadioButtonDefaults.colors(Color(0xFF379DF1))
                )
                //Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = Language.english,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }

    }
}