package com.magdy.wheatherapp.data.repository

import com.magdy.wheatherapp.data.mapper.toWeatherInfoObject
import com.magdy.wheatherapp.data.remote.WeatherApi
import com.magdy.wheatherapp.domin.repository.WeatherRepository
import com.magdy.wheatherapp.domin.util.Resource
import com.magdy.wheatherapp.domin.wheather.WeatherInfo
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api:WeatherApi
):WeatherRepository {


    override suspend fun getWeatherData(lat: Double, lang: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(data = api.getWeatherData(lat=lat, lang = lang).toWeatherInfoObject())
        }catch (e:Exception){
            e.printStackTrace()
            Resource.Error(e.localizedMessage!!)
        }
    }
}