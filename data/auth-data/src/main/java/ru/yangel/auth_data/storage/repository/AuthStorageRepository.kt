package ru.yangel.auth_data.storage.repository

interface AuthStorageRepository {
    suspend fun setTrainingPassed(isTrainingPassed: Boolean)
}