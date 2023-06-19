package com.weather.data.datasorce

import com.weather.data.datasorce.weatherapi.WeatherApi
import javax.inject.Inject

class ForecastRemoteDataSource @Inject constructor(private val api: WeatherApi) {
    suspend fun getForecastData(latitude: Double, longitude: Double) =
        api.getForecastData(latitude, longitude)

    suspend fun getForecastDataWithCityName(cityName: String) =
        api.getForecastDataWithCityName(cityName)
}