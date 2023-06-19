# WeatherApp 🌡

WeatherApp is an application that shows you the weather according to your location. You can save the weather of the city you want. If you have an internet connection, the weather conditions of these cities are updated automatically. Weather information is saved on the device.

<br/>

You can enter your API key in the location specified in the Constants.kt file in the utilites folder.


## Api 📦
* [OpenWeather Call 5 day / 3 hour forecast data](https://openweathermap.org/forecast5)

## Libraries 📚

* [Navigation](https://developer.android.com/jetpack/compose/navigation)

* [Retrofit](https://square.github.io/retrofit)

* [Location](https://developer.android.com/training/location)

* [ViewModel](https://developer.android.com/jetpack/compose/libraries#viewmodel)

* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)

* [Room](https://developer.android.com/jetpack/androidx/releases/room)

* [Accompanist](https://goo[..](..)gle.github.io/accompanist/insets/)

## Assets 🖼

* [Weather Icons](https://designdkblog.blogspot.com/2021/05/get-3d-weather-icons-for-adobe-xd-figma.html)
* <a href="https://www.flaticon.com/free-icons/error" title="error icons">Error icons created by Freepik - Flaticon</a>

### Home Screen 🏠


https://github.com/praveenyadati/Weather/assets/3103882/4ac4bb1c-d5c3-479a-be10-a19e33d260f3



## Project Structure 🏗

```
...
weatherapp
│
|
└───app
|   |   WeatherApplication.kt
|   |
|   └───theme
|   |   |
|   |   |   Color.kt
|   |   |   Shape.kt
|   |   |   Theme.kt
|   |   |   Type.kt
|
└───core
|   |
|   └───di
|   |   |   AppModule
|   |   |   DispatcherModule
|   |   |   LocationModule
|   |   |   RepositoryModule
|   |   |   SharedPreferencesModule
|   |   |   UseCaseModule
|   |   
|   └───helpers
|   |   |   EpochConverter
|   |   |   HourConverter
|   |   |   SetError
|   
└───data
|   |
|   └───datasource
|   |   |
|   |   └───remote
|   |   |   |   ForecastRemoteDataSource
|   |   |   |
|   |   |   └───entity
|   |   |   |   |   |   CityDto
|   |   |   |   |   |   CloudinessDto
|   |   |   |   |   |   CloudsDto
|   |   |   |   |   |   CoordDto
|   |   |   |   |   |   ForecastDto
|   |   |   |   |   |   ForecastWeatherDto
|   |   |   |   |   |   MainDto
|   |   |   |   |   |   SysDto
|   |   |   |   |   |   WeatherDto
|   |   |   |   |   |   WindDto
|   |   |   |
|   |   |   └───weatherapi
|   |   |   |   |   WeatherApi
|   |
|   └───location
|   |   |   DefaultLocationTracker
|   |
|   └───mapper
|   |   |   ForecastDtoMapper
|   |
|   └───repository
|   |   |   ForecastRepositoryImpl
|
└───domain
|   |
|   └───location
|   |   |   LocationTracker
|   |
|   └───mapper
|   |   |   IEntityMapper
|   |
|   └───model
|   |   |   City
|   |   |   Cloudiness
|   |   |   Clouds
|   |   |   Coord
|   |   |   Forecast
|   |   |   ForecastWeather
|   |   |   Main
|   |   |   MyCity
|   |   |   Sys
|   |   |   Weather
|   |   |   Wind
|   |
|   └───repository
|   |   |   ForecastRepository
|   |
|   └───usecase
|   |   |
|   |   └───forecast
|   |   |   |   GetForecastUseCase
|   |   |   |   GetForecastWithCityNameUseCase
|   |   |
|   |   └───location
|   |      |   GetLocationUseCase 
|   |
|   └───util
|   |   |   Resource
└───presentation
|   |
|   └───component
|   |   |   CircularProgressBar.kt
|   |   |   CurrentWeatherDetailRow.kt
|   |   |   ErrorCard.kt
|   |   |   ForecastLazyRow.kt
|   |   |   ForecastTitle
|   |
|   └───home
|   |   |   HomeForecastState
|   |   |   HomeScreen.kt
|   |   |   HomeViewModel
|   |
|   └───main
|   |   |   MainActivity
|   |
|   └───navigation
|   |   |   NavGraph.kt
|   |   |   NavRoutes
|   |   |   NavScreen
└───utils
|   |   Constants
|   |   WeatherType

```

