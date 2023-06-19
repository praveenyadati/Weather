package com.weather.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.core.common.DispatcherProvider
import com.weather.core.common.Resource
import com.weather.core.utils.ExceptionTitles
import com.weather.domain.usecase.forecast.GetForecastUseCase
import com.weather.domain.usecase.forecast.PreferencesUseCase
import com.weather.domain.usecase.location.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getForecast: GetForecastUseCase,
    private val getCurrentLocation: GetLocationUseCase,
    private val preferencesUseCase: PreferencesUseCase,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    private val _homeForecastState = MutableStateFlow<HomeForecastState>(HomeForecastState.Loading)
    val homeForecastState = _homeForecastState.asStateFlow()

    var searchFieldValue by mutableStateOf("")
        private set

    fun loadLocation() {
        _homeForecastState.value = HomeForecastState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                val result = preferencesUseCase.getPreviousCity()
                val cityName = result.getOrNull()
                if (!cityName.isNullOrBlank()) {
                    updateSearchField(cityName)
                    searchCityClick()
                } else {
                    val locationData = getCurrentLocation.getLocation()
                    if (locationData != null) {
                        fetchForecast(locationData.latitude, locationData.longitude)
                    } else {
                        _homeForecastState.value =
                            HomeForecastState.Error(ExceptionTitles.NO_INTERNET_CONNECTION)
                    }
                }
            } catch (e: Exception) {
                _homeForecastState.value = HomeForecastState.Error(e.message)
            }
        }
    }

    fun clear() {
        viewModelScope.launch(dispatcherProvider.io) {
            updateSearchField("")
            preferencesUseCase.setPreviousCity("")
            loadLocation()
        }
    }

    fun searchCityClick() {
        _homeForecastState.value = HomeForecastState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            try {
                fetchForecastWithCityName(searchFieldValue)
            } catch (e: Exception) {
                _homeForecastState.value = HomeForecastState.Error(e.message)
            }
        }
    }

    private suspend fun fetchForecastWithCityName(cityName: String) {
        when (val result = getForecast.getForecast(cityName)) {
            is Resource.Success -> {
                _homeForecastState.value = HomeForecastState.Success(result.data)
                preferencesUseCase.setPreviousCity(cityName)
            }

            is Resource.Error -> {
                _homeForecastState.value = HomeForecastState.Error(result.message)
            }
        }
    }

    private suspend fun fetchForecast(latitude: Double, longitude: Double) {
        when (val result = getForecast.getForecast(latitude, longitude)) {
            is Resource.Success -> {
                _homeForecastState.value = HomeForecastState.Success(result.data)
            }

            is Resource.Error -> {
                _homeForecastState.value = HomeForecastState.Error(result.message)
            }
        }
    }

    fun updateSearchField(input: String) {
        searchFieldValue = input
    }

}