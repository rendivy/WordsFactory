package ru.yangel.dictionary_data.observer

import kotlinx.coroutines.flow.StateFlow

interface WordsObservable<T> {

    val state: StateFlow<T>

    suspend fun notifyObservers(value: T)
}

