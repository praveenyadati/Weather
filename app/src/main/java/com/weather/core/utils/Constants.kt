package com.weather.core.utils

object NetworkService {
    const val BASE_URL: String = "https://api.openweathermap.org"
    const val API_KEY: String = "b03cbb933f8a2b2795c43ba0b3168870"
    const val UNITS: String = "metric"
    const val FORECAST_END_POINT = "/data/2.5/forecast"
}

object Constants {
    const val UNKNOWN_ERROR = "An unknown error occurred."
}

object ExceptionTitles {
    const val GPS_DISABLED = "GPS Disabled"
    const val NO_PERMISSION = "No Permission"
    const val NO_INTERNET_CONNECTION = "No Internet Connection"
    const val UNKNOWN_ERROR = "Unknown Error"
}

object ExceptionDescriptions {
    const val GPS_DISABLED_DESCR = "Your GPS seems to be disabled, please enable it."
    const val NO_PERMISSION_DESCR = "Allow otherwise location tracking won't work."
    const val NO_INTERNET_CONNECTION_DESCR = "Please check your internet connection."
    const val UNKNOWN_ERROR_DESCR = "Something went wrong."
}

object WeatherConditions {
    const val CLEAR_SKY = "clear sky"
    const val FEW_CLOUDS = "few clouds"
    const val SCATTERED_CLOUDS = "scattered clouds"
    const val BROKEN_CLOUDS = "broken clouds"
    const val SHOWER_RAIN = "shower rain"
    const val RAIN = "rain"
    const val THUNDERSTORM = "thunderstorm"
    const val SNOW = "snow"
    const val MIST = "mist"
}

object MainWeatherConditions {
    const val CLOUDS = "Clouds"
    const val SNOW = "Snow"
    const val RAIN = "Rain"
    const val THUNDERSTORM = "Thunderstorm"
    const val CLEAR = "Clear"
}