package com.weather.domain.usecase.forecast

import com.weather.data.repository.ForecastRepositoryImpl
import com.weather.domain.model.Forecast
import com.weather.core.common.Resource
import javax.inject.Inject

interface GetForecastUseCase {
    suspend fun getForecast(latitude: Double, longitude: Double): Resource<Forecast>

    suspend fun getForecast(cityName: String): Resource<Forecast>
}

class GetForecastUseCaseImpl @Inject constructor(
    private val forecastRepositoryImpl: ForecastRepositoryImpl) : GetForecastUseCase {
    override suspend fun getForecast(latitude: Double, longitude: Double): Resource<Forecast> =
        forecastRepositoryImpl.getForecastData(latitude, longitude)

    override suspend fun getForecast(cityName: String) =
        forecastRepositoryImpl.getForecastDataWithCityName(cityName)
}