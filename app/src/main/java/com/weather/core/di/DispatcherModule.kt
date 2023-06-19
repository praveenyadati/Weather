package com.weather.core.di

import com.weather.core.common.DefaultDispatcherProvider
import com.weather.core.common.DispatcherProvider
import com.weather.domain.usecase.forecast.GetForecastUseCase
import com.weather.domain.usecase.forecast.GetForecastUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(defaultDispatcherProvider: DefaultDispatcherProvider): DispatcherProvider
}