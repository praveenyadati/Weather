package com.weather.core.di

import com.weather.domain.usecase.forecast.GetForecastUseCase
import com.weather.domain.usecase.forecast.GetForecastUseCaseImpl
import com.weather.domain.usecase.forecast.PreferencesUseCase
import com.weather.domain.usecase.forecast.PreferencesUseCaseImpl
import com.weather.domain.usecase.location.GetLocationUseCase
import com.weather.domain.usecase.location.GetLocationUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGetForecastUseCase(getForecastUseCaseImpl: GetForecastUseCaseImpl): GetForecastUseCase

    @Binds
    @Singleton
    abstract fun bindGetLocationUseCase(getLocationUseCaseImpl: GetLocationUseCaseImpl): GetLocationUseCase

    @Binds
    @Singleton
    abstract fun bindPreferencesUseCase(preferencesUseCaseImpl: PreferencesUseCaseImpl): PreferencesUseCase
}