package ru.yangel.auth_data.storage.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yangel.auth_data.storage.repository.AuthRepository
import ru.yangel.auth_data.storage.repository.AuthRepositoryImpl
import ru.yangel.auth_data.storage.storage.AuthDataStorage
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    internal abstract fun bindsAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

}

@Module
@InstallIn(SingletonComponent::class)
object DataStorageModule {

    @Provides
    @Singleton
    fun provideAuthLocalDataSource(@ApplicationContext context: Context): AuthDataStorage {
        return AuthDataStorage(context)
    }

}


