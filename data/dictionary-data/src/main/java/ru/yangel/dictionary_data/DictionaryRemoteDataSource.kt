package ru.yangel.dictionary_data

import com.yuriyyangel.dictionaryapi.DictionaryApi
import com.yuriyyangel.dictionaryapi.models.WordResponseItem
import javax.inject.Inject

internal class DictionaryRemoteDataSource @Inject constructor(private val dictionaryApi: DictionaryApi) {

    suspend fun getWordDefinition(word: String): List<WordResponseItem> {
        return dictionaryApi.getWordDefinition(word).getOrThrow()
    }

}