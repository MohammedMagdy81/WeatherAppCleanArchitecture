package com.magdy.wheatherapp.domin.repository

import com.magdy.wheatherapp.domin.util.Resource
import com.magdy.wheatherapp.domin.wheather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat:Double,long:Double):Resource<WeatherInfo>
}