package com.yuriyyangel.wordsfactory.data

import kotlinx.coroutines.flow.Flow

interface StorageRepository {

    suspend fun isTrainingPassed(): Flow<Boolean>
}