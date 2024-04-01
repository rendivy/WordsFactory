package ru.yangel.auth_data.storage.repository

import android.content.Intent
import android.content.IntentSender
import com.google.firebase.Firebase
import ru.yangel.auth_data.storage.AuthLocalDataSource
import ru.yangel.auth_data.storage.GoogleAuthClient
import javax.inject.Inject


internal class AuthRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource,
    private val googleAuthClient: GoogleAuthClient
) : AuthRepository {

    override fun isUserLogin(): Boolean {
        return googleAuthClient.isUserAlreadyLogin()
    }

    override suspend fun signUpWithIntent(intent: Intent) {
        googleAuthClient.signInWithIntent(intent)
    }

    override suspend fun signInWithGoogle(): IntentSender? {
        return googleAuthClient.signIn()
    }

    override suspend fun registerUser(email: String, password: String) {
        googleAuthClient.registerUser(
            email = email,
            password = password
        )
    }

    override suspend fun signInWithEmailAndPassword(email: String, password: String) {
        googleAuthClient.signInWithEmailAndPassword(
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