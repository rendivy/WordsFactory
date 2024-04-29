package com.yuriyyangel.wordsfactory.data

import kotlinx.coroutines.flow.Flow
import ru.yangel.auth_data.storage.storage.AuthDataStorage
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(private val authDataStorage: AuthDataStorage) : StorageRepository {

    override suspend fun isTrainingPassed(): Flow<Boolean> {
        return authDataStorage.isTrainingPassed()
    }
}