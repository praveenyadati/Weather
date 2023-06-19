package com.weather.domain.repository

interface PreferencesRepository {

    suspend fun setPreviousCity(name: String)

    suspend fun getPreviousCity(): Result<String>
}