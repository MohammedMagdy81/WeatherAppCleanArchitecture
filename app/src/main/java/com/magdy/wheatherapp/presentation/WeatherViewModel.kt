package com.magdy.wheatherapp.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magdy.wheatherapp.domin.location.LocationTracker
import com.magdy.wheatherapp.domin.repository.WeatherRepository
import com.magdy.wheatherapp.domin.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
    private val repository: WeatherRepository
):ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set
    fun loadWeatherInfo(){
        viewModelScope.launch {
            state=state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let {location ->
                Log.d("TAGLocation", "Lat = ${location.latitude} / lang = ${location.longitude} ")
                when(val result= repository.getWeatherData(location.latitude,location.longitude)){
                    is Resource.Success->{
                        state=state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error->{
                        state=state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }?: kotlin.run {
                state=state.copy(
                    weatherInfo = null,
                    isLoading = false,
                    error = "Can't Retrieve Your Location ! "
                )
            }
        }
    }

}