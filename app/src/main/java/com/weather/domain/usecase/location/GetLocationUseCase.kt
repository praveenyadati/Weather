package com.weather.domain.usecase.location

import android.location.Location
import com.weather.data.location.DefaultLocationTracker
import javax.inject.Inject

interface GetLocationUseCase {

    suspend fun getLocation(): Location?
}

class GetLocationUseCaseImpl @Inject constructor
    (private val defaultLocationTracker: DefaultLocationTracker) : GetLocationUseCase {
    override suspend fun getLocation() = defaultLocationTracker.getCurrentLocation()
}