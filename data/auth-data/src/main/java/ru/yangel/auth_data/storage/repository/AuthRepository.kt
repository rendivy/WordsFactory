package ru.yangel.auth_data.storage.repository

interface AuthRepository {
    fun isUserLogin(): Boolean
    suspend fun signInWithGoogle()
    suspend fun registerUser(email: String, password: String)
    fun loginUser()
    fun logout()
}