package ru.yangel.dictionary_data.repository

import ru.yangel.dictionary_data.model.WordDTO

interface DictionaryRepository {
    suspend fun getWordWithDefinition(word: String) : List<WordDTO>
}