package com.magdy.wheatherapp.domin.wheather

data class WeatherInfo(

    val weatherDataPerDay:Map<Int,List<WeatherData>>,
    val currentWeatherData:WeatherData?
)
