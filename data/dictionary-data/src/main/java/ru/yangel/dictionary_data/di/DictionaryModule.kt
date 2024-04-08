package ru.yangel.dictionary_data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yangel.dictionary_data.repository.DictionaryRepository
import ru.yangel.dictionary_data.repository.DictionaryRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
abstract class DictionaryModule {


    @Binds
    internal abstract fun dictionaryRepositoryBinds(dictionaryRepositoryImpl: DictionaryRepositoryImpl) : DictionaryRepository
}