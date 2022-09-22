package com.magdy.wheatherapp.di

import com.magdy.wheatherapp.data.location.LocationTrackerImpl
import com.magdy.wheatherapp.data.repository.WeatherRepositoryImpl
import com.magdy.wheatherapp.domin.location.LocationTracker
import com.magdy.wheatherapp.domin.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImpl: WeatherRepositoryImpl):WeatherRepository
}