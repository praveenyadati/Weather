package com.weather.presentation.home

import com.weather.domain.model.Forecast

sealed interface HomeForecastState {
    data class Success(val forecast: Forecast?): HomeForecastState
    data class Error(val errorMessage: String?): HomeForecastState

    object Loading: HomeForecastState
}