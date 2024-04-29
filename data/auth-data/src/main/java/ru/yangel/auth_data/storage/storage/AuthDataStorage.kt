package ru.yangel.auth_data.storage.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class AuthDataStorage(private val context: Context) {

    private val USER_PREFS_KEY = booleanPreferencesKey("is_user_exists")
    private val TRAINING_PREFS_KEY = booleanPreferencesKey("is_training_passed_today")

    suspend fun setTrainingPassed(isTrainingPassed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[TRAINING_PREFS_KEY] = isTrainingPassed
        }
    }

    fun isTrainingPassed(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[TRAINING_PREFS_KEY] ?: false
        }
    }

    suspend fun resetTrainingPassed() {
        context.dataStore.edit { preferences ->
            preferences[TRAINING_PREFS_KEY] = false
        }
    }


    fun isOnboardingPassed(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_PREFS_KEY] ?: false
        }
    }

    suspend fun setUserLogin(isUserLogin: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[USER_PREFS_KEY] = isUserLogin
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}
