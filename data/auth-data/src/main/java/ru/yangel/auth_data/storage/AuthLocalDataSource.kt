package ru.yangel.auth_data.storage

import ru.yangel.auth_data.storage.storage.AuthDataStorage
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(private val authDataStorage: AuthDataStorage) {

    fun isUserLogin(): Boolean {
        return authDataStorage.isUserLogin()
    }

    fun setUserLogin(isUserLogin: Boolean) {
        authDataStorage.setUserLogin(isUserLogin)
    }

    fun clear() {
        authDataStorage.clear()
    }
}