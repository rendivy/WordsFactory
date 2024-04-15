package ru.yangel.dictionary_data.observer

import kotlinx.coroutines.channels.Channel

interface WordsObservable<T> {

    val buffer: Channel<T>
    suspend fun notifyObservers(value: T)
}

