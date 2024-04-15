package ru.yangel.dictionary_data.observer

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordsUpdateObservable @Inject constructor() : WordsObservable<Boolean> {

    override var buffer = Channel<Boolean>(Channel.CONFLATED)

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun notifyObservers(value: Boolean) {
        if (buffer.isClosedForSend) {
            buffer = Channel(Channel.CONFLATED)
        }
        buffer.trySend(value)
    }

}