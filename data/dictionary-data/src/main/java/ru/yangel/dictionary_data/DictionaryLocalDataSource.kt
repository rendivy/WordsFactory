package ru.yangel.dictionary_data

import com.keyinc.words.database.WordsDatabase
import com.keyinc.words.database.entity.DefinitionDBO
import com.keyinc.words.database.entity.MeaningDBO
import com.keyinc.words.database.entity.PhoneticDBO
import com.keyinc.words.database.entity.WordDBO
import com.keyinc.words.database.entity.WordWithAllAttributes
import java.util.Locale
import javax.inject.Inject

internal class DictionaryLocalDataSource @Inject constructor(private val database: WordsDatabase) {

    suspend fun saveWordInLocalSource(
        word: WordDBO,
        meaningList: List<MeaningDBO>,
        definitionList: List<List<DefinitionDBO>>,
        phoneticList: List<PhoneticDBO>,
    ) {
        database.runInTransaction {
            database.wordDao.insertWord(wordDBO = word)
            meaningList.forEach { meaning ->
                database.meaningDao.insertMeaning(meaningDBO = meaning)
            }
            definitionList.forEach { definitionList ->
                definitionList.forEach { definition ->
                    database.definitionDao.insertDefinition(definitionDBO = definition)
                }
            }
            phoneticList.forEach { phonetic ->
                database.phoneticDao.insertPhonetic(phoneticDBO = phonetic)
            }
        }
    }

    suspend fun getWordsWithLowSkillRatio(): List<WordWithAllAttributes>? {
        return database.wordDao.getWordsWithLowSkillRatio()
    }

    suspend fun getAllWordFromLocalStore(): List<WordWithAllAttributes>? {
        return database.wordDao.getAllWords()
    }

    suspend fun getWordFromLocalStore(word: String): WordWithAllAttributes? {
        return database.wordDao.getWordWithMeanings(word.lowercase(Locale.getDefault()))
    }

    suspend fun getWordsCount(): Int {
        return database.wordDao.getWordsCount()
    }
}