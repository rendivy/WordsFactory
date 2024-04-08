package com.yuriyyangel.wordsfactory.di


import com.yuriyyangel.dictionaryapi.DictionaryApi
import com.yuriyyangel.wordsfactory.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesDictionaryApi(): DictionaryApi {
        return DictionaryApi(
            baserUrl = BuildConfig.DICTIONARY_BASE_API_URL
        )
    }


}