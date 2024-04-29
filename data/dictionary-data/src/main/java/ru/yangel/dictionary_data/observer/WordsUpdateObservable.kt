package ru.yangel.dictionary_data.observer

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordsUpdateObservable @Inject constructor() : WordsObservable<Int> {

    private val mutableState = MutableStateFlow(0)
    override val wordInDictionaryState: StateFlow<Int> = mutableState

    private val _rememberedWordState = MutableStateFlow(0)
    override val rememberedWordState: StateFlow<Int> = _rememberedWordState

    override suspend fun notifyObservers(value: Int, rememberedCount: Int) {
        mutableState.value = value
        _rememberedWordState.value = rememberedCount

    }
}