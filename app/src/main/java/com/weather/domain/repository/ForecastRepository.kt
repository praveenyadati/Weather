package com.weather.domain.repository

import com.weather.domain.model.Forecast
import com.weather.core.common.Resource

interface ForecastRepository {
    suspend fun getForecastData(latitude: Double, longitude: Double, ): Resource<Forecast>

    suspend fun getForecastDataWithCityName(cityName: String): Resource<Forecast>

}