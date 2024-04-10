package ru.yangel.dictionary_data.repository

import com.keyinc.words.database.entity.DefinitionDBO
import ru.yangel.dictionary_data.DictionaryLocalDataSource
import ru.yangel.dictionary_data.mappers.WordToEntityMapper
import ru.yangel.dictionary_data.model.WordDTO
import javax.inject.Inject

internal class WordRepositoryImpl @Inject constructor(private val localDataSource: DictionaryLocalDataSource) :
    WordRepository {

    private val wordToEntityMapper = WordToEntityMapper()

    override suspend fun saveWord(wordDTO: WordDTO) {
        val meaningList = wordToEntityMapper.transformMeaning(wordDTO, wordDTO.meanings)
        val fullDefinitionList: List<List<DefinitionDBO>> = wordDTO.meanings.map { it.definitions }
            .map { definitionList ->
                wordToEntityMapper.transformDefinition(
                    wordDTO,
                    definitionList
                )
            }
        val phoneticList = wordToEntityMapper.transformPhonetic(wordDTO, wordDTO.phonetics)
        localDataSource.saveWordInLocalSource(
            word = wordToEntityMapper.transform(wordDTO),
            meaningList = meaningList,
            definitionList = fullDefinitionList,
            phoneticList = phoneticList
        )
    }

    override suspend fun getAllWord(): List<WordDTO> {
        TODO("Not yet implemented")
    }

}