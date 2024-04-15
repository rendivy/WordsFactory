package com.yuriyyangel.wordsfactory.di


import android.content.Context
import com.keyinc.words.database.WordsDatabase
import com.yuriyyangel.dictionaryapi.DictionaryApi
import com.yuriyyangel.wordsfactory.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yangel.core.di.AppDispatchers
import ru.yangel.dictionary_data.observer.WordsObservable
import ru.yangel.dictionary_data.repository.WordRepository
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
    fun provideAppDispatchers() : AppDispatchers {
        return AppDispatchers()
    }


}


@EntryPoint
@InstallIn(SingletonComponent::class)
interface WidgetFactoryAppDependencies {
    fun wordRepository(): WordRepository
    fun wordsObservable(): WordsObservable<Boolean>
}