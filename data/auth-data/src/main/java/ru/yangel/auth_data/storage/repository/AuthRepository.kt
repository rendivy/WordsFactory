package ru.yangel.auth_data.storage.repository

interface AuthRepository {
    fun isUserLogin(): Boolean
    fun registerUser()
    fun loginUser()
    fun logout()
}