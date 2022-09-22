package com.magdy.wheatherapp.data.mapper

import com.magdy.wheatherapp.data.remote.WeatherDataDto
import com.magdy.wheatherapp.data.remote.WeatherDto
import com.magdy.wheatherapp.domin.wheather.WeatherData
import com.magdy.wheatherapp.domin.wheather.WeatherInfo
import com.magdy.wheatherapp.domin.wheather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {

    return time.mapIndexed { index, time ->

        val temperature = temperatures[index]
        val windSpeed = windSpeeds[index]
        val weatherCode = weatherCodes[index]
        val humidity = humidities[index]
        val pressure = pressures[index]
        IndexWeatherData(
            index = index,
            WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)

            )
        )

    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map {
            it.data
        }
    }
}


fun WeatherDto.toWeatherInfoObject(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val houre = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == houre
    }

    return WeatherInfo(weatherDataPerDay = weatherDataMap, currentWeatherData = currentWeatherData)

}






