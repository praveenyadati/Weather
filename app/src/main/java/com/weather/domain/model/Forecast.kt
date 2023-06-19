package com.weather.domain.model

data class Forecast(
    val weatherList: List<ForecastWeather>,
    val cityDtoData: City
)
