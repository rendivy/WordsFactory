package ru.yangel.auth_data.storage.repository

import android.content.Intent
import android.content.IntentSender
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun isUserLogin(): Boolean
    fun isOnboardingPassed(): Flow<Boolean>
    suspend fun passOnboarding()
    suspend fun signUpWithIntent(intent: Intent)
    suspend fun signInWithGoogle(): IntentSender?
    suspend fun registerUser(email: String, password: String)
    suspend fun signInWithEmailAndPassword(email: String, password: String)
    suspend fun loginUser()
    suspend fun logout()
}