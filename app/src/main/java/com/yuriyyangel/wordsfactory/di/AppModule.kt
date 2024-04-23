package com.yuriyyangel.wordsfactory.di


import android.content.Context
import com.keyinc.words.database.WordsDatabase
import com.yuriyyangel.dictionaryapi.DictionaryApi
import com.yuriyyangel.wordsfactory.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yangel.core.di.AppDispatchers
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


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): WordsDatabase {
        return WordsDatabase(context)
    }

    @Singleton
    @Provides
    fun provideAppDispatchers(): AppDispatchers {
        return AppDispatchers()
    }

}


