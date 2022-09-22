package com.magdy.wheatherapp.presentation

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.magdy.wheatherapp.domin.wheather.WeatherData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherHourlyDisplay(
    weatherData: WeatherData,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {

    var timeFormat = remember(weatherData) {

        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")

        )

    }

    Column(
        modifier = modifier,
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = timeFormat,
            color = Color.LightGray
        )
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = "${weatherData.temperatureCelsius} °C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}