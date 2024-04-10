package ru.yangel.dictionary_data.repository

import ru.yangel.dictionary_data.model.WordDTO

interface WordRepository {
    suspend fun saveWord(wordDTO: WordDTO)
    suspend fun getAllWord() : List<WordDTO>
}