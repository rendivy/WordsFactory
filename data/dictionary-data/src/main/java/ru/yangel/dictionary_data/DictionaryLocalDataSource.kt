package ru.yangel.dictionary_data

import com.keyinc.words.database.WordsDatabase
import com.keyinc.words.database.entity.DefinitionDBO
import com.keyinc.words.database.entity.MeaningDBO
import com.keyinc.words.database.entity.PhoneticDBO
import com.keyinc.words.database.entity.WordDBO
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
        }
    }
}