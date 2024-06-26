package ru.yangel.dictionary_data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yangel.dictionary_data.observer.WordsObservable
import ru.yangel.dictionary_data.observer.WordsUpdateObservable
import ru.yangel.dictionary_data.repository.DictionaryRepository
import ru.yangel.dictionary_data.repository.DictionaryRepositoryImpl
import ru.yangel.dictionary_data.repository.QuestionRepository
import ru.yangel.dictionary_data.repository.QuestionRepositoryImpl
import ru.yangel.dictionary_data.repository.WordRepository
import ru.yangel.dictionary_data.repository.WordRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
abstract class DictionaryModule {

    @Binds
    internal abstract fun dictionaryRepositoryBinds(dictionaryRepositoryImpl: DictionaryRepositoryImpl): DictionaryRepository

    @Binds
    internal abstract fun wordRepositoryBinds(dictionaryRepositoryImpl: WordRepositoryImpl): WordRepository

    @Binds
    internal abstract fun questionRepositoryBinds(questionRepositoryImpl: QuestionRepositoryImpl): QuestionRepository

    @Binds
    internal abstract fun wordsUpdateObservableBinds(wordsUpdateObservable: WordsUpdateObservable): WordsObservable<Int>
}


