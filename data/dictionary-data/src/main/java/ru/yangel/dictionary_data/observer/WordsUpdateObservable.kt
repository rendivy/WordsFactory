package ru.yangel.dictionary_data.observer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordsUpdateObservable @Inject constructor() : WordsObservable<Int> {

    private val mutableState = MutableStateFlow(0)
    override val state: StateFlow<Int> = mutableState

    override suspend fun notifyObservers(value: Int) {
        mutableState.value = value
    }
}