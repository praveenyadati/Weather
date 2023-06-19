package com.weather.data.mapper

import com.weather.data.datasorce.entity.ForecastDto
import com.weather.domain.mapper.IEntityMapper
import com.weather.domain.model.*
import javax.inject.Inject

class ForecastDtoMapper @Inject constructor() : IEntityMapper<ForecastDto, Forecast> {
    override fun mapFromEntity(entity: ForecastDto): Forecast {
        val forecastWeather: List<ForecastWeather> = entity.weatherList.map {
            ForecastWeather(
                weatherData = Main(
                    it.weatherData.temp,
                    it.weatherData.feelsLike,
                    it.weatherData.pressure,
                    it.weatherData.humidity
                ),
                weatherStatus = listOf(
                    Weather(it.weatherStatus[0].mainDescription, it.weatherStatus[0].description)
                ),
                wind = Wind(it.wind.speed),
                date = it.date,
                cloudiness = Cloudiness(it.cloudinessDto.cloudiness)
            )
        }

        return Forecast(
            forecastWeather,
            City(
                entity.cityDtoData.country,
                entity.cityDtoData.timezone,
                entity.cityDtoData.sunrise,
                entity.cityDtoData.sunset,
                entity.cityDtoData.cityName,
                Coord(
                    entity.cityDtoData.coordinate.latitude,
                    entity.cityDtoData.coordinate.longitude
                )
            )
        )
    }
}