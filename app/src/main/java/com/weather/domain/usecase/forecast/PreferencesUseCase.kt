package com.weather.domain.usecase.forecast

import com.weather.data.repository.PreferencesRepositoryImpl
import javax.inject.Inject

interface PreferencesUseCase {

    suspend fun setPreviousCity(name: String)

    suspend fun getPreviousCity(): Result<String>
}

class PreferencesUseCaseImpl @Inject constructor(
    private val preferencesRepositoryImpl: PreferencesRepositoryImpl
) : PreferencesUseCase {

    override suspend fun setPreviousCity(name: String) {
        preferencesRepositoryImpl.setPreviousCity(name)
    }

    override suspend fun getPreviousCity(): Result<String> {
        return preferencesRepositoryImpl.getPreviousCity()
    }

}