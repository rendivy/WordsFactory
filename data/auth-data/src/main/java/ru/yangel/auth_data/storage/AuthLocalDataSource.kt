package ru.yangel.auth_data.storage

import kotlinx.coroutines.flow.Flow
import ru.yangel.auth_data.storage.storage.AuthDataStorage
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(private val authDataStorage: AuthDataStorage) {

    fun isOnboardingPassed(): Flow<Boolean> {
        return authDataStorage.isOnboardingPassed()
    }

    suspend fun setUserLogin(isUserLogin: Boolean) {
        authDataStorage.setUserLogin(isUserLogin)
    }


    suspend fun clear() {
        authDataStorage.clear()
    }
}