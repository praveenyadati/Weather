package com.weather.presentation.home

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weather.R
import com.weather.core.helpers.EpochConverter
import com.weather.core.helpers.SetError
import com.weather.core.utils.ExceptionTitles
import com.weather.domain.model.Forecast
import com.weather.presentation.component.*

/**
 * Responsible to render home screen
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val homeCurrentWeatherState by viewModel.homeForecastState.collectAsState()
    val activity = (LocalContext.current as? Activity)

    Scaffold(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()
        SearchField(viewModel)
        WeatherSection(homeCurrentWeatherState) { activity?.finish() }
    }
}

/**
 * Responsible to render background image in home screen
 */
@Composable
private fun BackgroundImage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

/**
 * Responsible to render current place weather section in home screen
 */
@Composable
private fun WeatherSection(currentWeatherState: HomeForecastState, errorCardOnClick: () -> Unit) {
    when (currentWeatherState) {
        is HomeForecastState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressBar(modifier = Modifier.size(LocalConfiguration.current.screenWidthDp.dp / 3))
            }
        }

        is HomeForecastState.Success -> {
            if (currentWeatherState.forecast != null) {
                CurrentWeatherSection(currentWeatherState.forecast)
                DetailsSection(currentWeatherState.forecast)
            }
        }

        is HomeForecastState.Error -> {
            ErrorCard(
                modifier = Modifier.fillMaxSize(),
                errorTitle = currentWeatherState.errorMessage ?: ExceptionTitles.UNKNOWN_ERROR,
                errorDescription = SetError.setErrorCard(
                    currentWeatherState.errorMessage ?: ExceptionTitles.UNKNOWN_ERROR
                ),
                errorButtonText = stringResource(R.string.ok),
                errorCardOnClick,
                cardModifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp / 4 + 48.dp)
                    .padding(horizontal = 64.dp)
            )
        }
    }
}

/**
 * Responsible to render current place temperature in home screen
 */
@Composable
private fun CurrentWeatherSection(todayWeather: Forecast) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = todayWeather.cityDtoData.cityName,
            style = MaterialTheme.typography.h2
        )
        Text(
            text = "${todayWeather.weatherList[0].weatherData.temp.toInt()}${stringResource(R.string.degree)}",
            style = MaterialTheme.typography.h1
        )
        Text(
            text = todayWeather.weatherList[0].weatherStatus[0].description,
            style = MaterialTheme.typography.h3,
            color = Color.Gray
        )
        Text(
            text = "H:${todayWeather.cityDtoData.coordinate.longitude}°  L:${todayWeather.cityDtoData.coordinate.latitude}°",
            style = MaterialTheme.typography.h3
        )
    }
}

/**
 * Responsible to render current place other weather details in home screen
 */
@Composable
private fun DetailsSection(forecast: Forecast) {
    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 2),
            backgroundColor = MaterialTheme.colors.onSurface,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                ForecastSection(forecast)
                WeatherDetailSection(forecast)
            }
        }
    }
}

@Composable
private fun ForecastSection(forecastData: Forecast) {
    ForecastTitle(text = stringResource(R.string.hourly_forecast))
    ForecastLazyRow(forecasts = forecastData.weatherList.take(8))
    ForecastTitle(text = stringResource(R.string.daily_forecast))
    ForecastLazyRow(forecasts = forecastData.weatherList.take(8))
}

@Composable
private fun WeatherDetailSection(currentWeather: Forecast) {
    CurrentWeatherDetailRow(
        title1 = stringResource(R.string.temp),
        value1 = "${currentWeather.weatherList[0].weatherData.temp}${stringResource(R.string.degree)}",
        title2 = stringResource(R.string.feels_like),
        value2 = "${currentWeather.weatherList[0].weatherData.feelsLike}${stringResource(R.string.degree)}"
    )
    CurrentWeatherDetailRow(
        title1 = stringResource(R.string.cloudiness),
        value1 = "${currentWeather.weatherList[0].cloudiness.cloudiness}%",
        title2 = stringResource(R.string.humidity),
        value2 = "${currentWeather.weatherList[0].weatherData.humidity}%"
    )
    CurrentWeatherDetailRow(
        title1 = stringResource(R.string.sunrise),
        value1 = "${EpochConverter.readTimestamp(currentWeather.cityDtoData.sunrise)}AM",
        title2 = stringResource(R.string.sunset),
        value2 = "${EpochConverter.readTimestamp(currentWeather.cityDtoData.sunset)}PM"
    )
    CurrentWeatherDetailRow(
        title1 = stringResource(R.string.wind),
        value1 = "${currentWeather.weatherList[0].wind.speed}${stringResource(R.string.km)}",
        title2 = stringResource(R.string.pressure),
        value2 = "${currentWeather.weatherList[0].weatherData.pressure}"
    )
}

/**
 * Responsible to render search field where user can search any city within USA
 */
@Composable
private fun SearchField(viewModel: HomeViewModel) {
    val focusManager = LocalFocusManager.current

    val trailingIconView = @Composable {
        IconButton(onClick = {
            viewModel.clear()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.close_circle_svgrepo_com),
                contentDescription = null
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.searchFieldValue,
            onValueChange = { viewModel.updateSearchField(it) },
            label = {
                Text(
                    text = stringResource(R.string.search_for_a_city),
                    style = MaterialTheme.typography.h2.copy(fontSize = 14.sp),
                    color = Color.Gray)
            },
            textStyle = MaterialTheme.typography.h2.copy(fontSize = 16.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,

            ),
            keyboardActions = KeyboardActions(onSearch = {
                viewModel.searchCityClick()
                focusManager.clearFocus()
            }),
            maxLines = 1,
            trailingIcon =if (viewModel.searchFieldValue.isNotBlank()) trailingIconView else null
        )
    }
}