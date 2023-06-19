package com.weather.data.repository

import com.weather.data.datasorce.ForecastRemoteDataSource
import com.weather.data.mapper.ForecastDtoMapper
import com.weather.domain.model.Forecast
import com.weather.domain.repository.ForecastRepository
import com.weather.core.common.Resource
import com.weather.core.utils.Constants
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val forecastRemoteDataSource: ForecastRemoteDataSource,
    private val dtoMapper: ForecastDtoMapper
) : ForecastRepository {
    override suspend fun getForecastData(
        latitude: Double,
        longitude: Double
    ): Resource<Forecast> {
        return try {
            Resource.Success(
                dtoMapper.mapFromEntity(
                    forecastRemoteDataSource.getForecastData(
                        latitude,
                        longitude
                    )
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }

    override suspend fun getForecastDataWithCityName(cityName: String): Resource<Forecast> {
        return try {
            Resource.Success(
                dtoMapper.mapFromEntity(
                    forecastRemoteDataSource.getForecastDataWithCityName(cityName)
                )
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }
}