package ru.yangel.auth_data.storage.repository

import ru.yangel.auth_data.storage.AuthLocalDataSource
import ru.yangel.auth_data.storage.GoogleAuthClient
import javax.inject.Inject


internal class AuthRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val googleAuthClient: GoogleAuthClient
) : AuthRepository {

    override fun isUserLogin(): Boolean {
        return authLocalDataSource.isUserLogin()
    }

    override suspend fun signInWithGoogle() {
        googleAuthClient.signIn()
    }

    override suspend fun registerUser(email: String, password: String) {
        googleAuthClient.registerUser(
            email = email,
            password = password
        )
    }

    override fun loginUser() {
        authLocalDataSource.setUserLogin(true)
    }

    override fun logout() {
        authLocalDataSource.clear()
    }

}