package com.weather.presentation.home

import android.location.Location
import app.cash.turbine.test
import com.weather.core.common.DispatcherProvider
import com.weather.core.common.Resource
import com.weather.domain.model.City
import com.weather.domain.model.Coord
import com.weather.domain.model.Forecast
import com.weather.domain.usecase.forecast.GetForecastUseCase
import com.weather.domain.usecase.forecast.PreferencesUseCase
import com.weather.domain.usecase.location.GetLocationUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    private lateinit var getForecast: GetForecastUseCase

    @Mock
    private lateinit var getCurrentLocation: GetLocationUseCase

    @Mock
    private lateinit var location: Location

    private lateinit var dispatcherProvider: DispatcherProvider

    @Mock
    private lateinit var preferencesUseCase: PreferencesUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        viewModel = HomeViewModel(getForecast, getCurrentLocation,
            preferencesUseCase,dispatcherProvider)
        doReturn(0.0).`when`(location).latitude
        doReturn(0.0).`when`(location).longitude
    }

    @Test
    fun `Given loadLocation, then it waits for event success`() {
        runTest {

            //given
            val city = City(
                country = "USA",
                timezone = 0,
                sunrise = 12L,
                sunset = 4L,
                cityName = "Austin",
                coordinate = Coord(0.0, 0.0)
            )
            val foreCast = Forecast(listOf(), city)
            doReturn(location).`when`(getCurrentLocation).getLocation()
            doReturn(Resource.Success(foreCast)).`when`(getForecast).getForecast(0.0, 0.0)

            //execute
            viewModel.loadLocation()

            //test
            viewModel.homeForecastState.test {
                assertEquals(HomeForecastState.Success(foreCast), awaitItem())
            }
        }
    }

    @Test
    fun `Given loadLocation, then it waits for event failure`() {
        runTest {

            //given
            doReturn(location).`when`(getCurrentLocation).getLocation()
            doReturn(Resource.Error("error", null)).
            `when`(getForecast).getForecast(0.0, 0.0)

            //execute
            viewModel.loadLocation()

            //test
            viewModel.homeForecastState.test {
                assertEquals(HomeForecastState.Error("error"), awaitItem())
            }
        }
    }
}