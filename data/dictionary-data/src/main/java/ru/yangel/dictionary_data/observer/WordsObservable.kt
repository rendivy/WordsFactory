package ru.yangel.dictionary_data.observer

import kotlinx.coroutines.flow.StateFlow

interface WordsObservable<T> {

    val wordInDictionaryState: StateFlow<T>
    val rememberedWordState: StateFlow<T>

    suspend fun notifyObservers(value: T, rememberedCount: T)
}

