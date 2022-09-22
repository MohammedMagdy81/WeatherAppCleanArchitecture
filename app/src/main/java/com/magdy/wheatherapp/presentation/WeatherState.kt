package com.magdy.wheatherapp.presentation

import com.magdy.wheatherapp.domin.wheather.WeatherInfo

data class WeatherState(
    val weatherInfo:WeatherInfo?=null,
    val isLoading:Boolean=false,
    val error:String?=null
)
