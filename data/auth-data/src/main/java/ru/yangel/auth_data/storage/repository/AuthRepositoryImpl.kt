package ru.yangel.auth_data.storage.repository

import ru.yangel.auth_data.storage.AuthLocalDataSource
import javax.inject.Inject


internal class AuthRepositoryImpl @Inject constructor(private val authLocalDataSource: AuthLocalDataSource) :
    AuthRepository {

    override fun isUserLogin(): Boolean {
        return authLocalDataSource.isUserLogin()
    }

    override fun registerUser() {
        authLocalDataSource.setUserLogin(true)
    }

    override fun loginUser() {
        authLocalDataSource.setUserLogin(true)
    }

    override fun logout() {
        authLocalDataSource.clear()
    }

}