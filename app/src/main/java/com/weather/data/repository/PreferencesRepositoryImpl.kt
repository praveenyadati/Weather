package com.weather.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.weather.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val storePreferences: DataStore<Preferences>
) : PreferencesRepository {

    private companion object {
        val KEY_NAME = stringPreferencesKey(name = "city_name")
    }

    override suspend fun setPreviousCity(name: String) {
        Result.runCatching {
            storePreferences.edit { preferences ->
                preferences[KEY_NAME] = name
            }
        }
    }

    override suspend fun getPreviousCity(): Result<String> {
        return Result.runCatching {
            val flow = storePreferences.data
                .catch { exception ->
                    if (exception is IOException) {
                        emit(emptyPreferences())
                    } else {
                        throw exception
                    }
                }
                .map { preferences ->
                    preferences[KEY_NAME]
                }
            val value = flow.firstOrNull() ?: ""
            value
        }
    }
}