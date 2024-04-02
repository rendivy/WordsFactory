package ru.yangel.auth_data.storage.repository

import android.content.Intent
import android.content.IntentSender

interface AuthRepository {
    fun isUserLogin(): Boolean
    suspend fun signUpWithIntent(intent: Intent)
    suspend fun signInWithGoogle(): IntentSender?
    suspend fun registerUser(email: String, password: String)
    suspend fun signInWithEmailAndPassword(email: String, password: String)
    fun loginUser()
    fun logout()
}