package ru.yangel.dictionary_data.repository

import com.keyinc.words.database.entity.DefinitionDBO
import ru.yangel.dictionary_data.DictionaryLocalDataSource
import ru.yangel.dictionary_data.mappers.WordToEntityMapper
import ru.yangel.dictionary_data.mappers.WordWithMeaningToDtoMapper
import ru.yangel.dictionary_data.model.WordDTO
import ru.yangel.dictionary_data.observer.WordsObservable
import javax.inject.Inject

internal class WordRepositoryImpl @Inject constructor(
    private val localDataSource: DictionaryLocalDataSource,
    private val wordsUpdateObservable: WordsObservable<Int>
) : WordRepository {

    private val wordToEntityMapper = WordToEntityMapper()
    private val wordWithMeaningToDtoMapper = WordWithMeaningToDtoMapper()

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
        getWordsCount()
    }

    override suspend fun getWordFromLocalSource(word: String): WordDTO? {
        val wordEntity = localDataSource.getWordFromLocalStore(word)
        return wordEntity?.let { wordWithMeaningToDtoMapper.transform(it) }
    }

    override suspend fun getAllWord(): List<WordDTO> {
        return localDataSource.getAllWordFromLocalStore()
            ?.map { wordWithMeaningToDtoMapper.transform(it) } ?: emptyList()
    }

    override suspend fun getWordsCount(): Int {
        val databaseCount = localDataSource.getWordsCount()
        val rememberedCount = localDataSource.getRememberedWordCount()
        wordsUpdateObservable.notifyObservers(databaseCount, rememberedCount)
        return localDataSource.getWordsCount()
    }

}