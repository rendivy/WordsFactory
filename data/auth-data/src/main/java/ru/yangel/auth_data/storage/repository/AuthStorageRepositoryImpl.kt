package ru.yangel.auth_data.storage.repository

import ru.yangel.auth_data.storage.storage.AuthDataStorage
import javax.inject.Inject

class AuthStorageRepositoryImpl @Inject constructor(private val authDataStorage: AuthDataStorage) :
    AuthStorageRepository {

    override suspend fun setTrainingPassed(isTrainingPassed: Boolean) {
        authDataStorage.setTrainingPassed(isTrainingPassed)
    }
}