package ru.yangel.dictionary_data.repository

import ru.yangel.dictionary_data.DictionaryRemoteDataSource
import ru.yangel.dictionary_data.mappers.WordMapper
import ru.yangel.dictionary_data.model.WordDTO
import javax.inject.Inject

internal class DictionaryRepositoryImpl @Inject constructor(private val dictionaryRemoteDataSource: DictionaryRemoteDataSource) :
    DictionaryRepository {

    override suspend fun getWordWithDefinition(word: String): List<WordDTO> {

        return dictionaryRemoteDataSource.getWordDefinition(word = word).map {
            WordMapper.mapWordResponseItemToWord(it)
        }
    }

}