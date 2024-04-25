package com.keyinc.words.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.keyinc.words.database.entity.WordDBO
import com.keyinc.words.database.entity.WordWithAllAttributes


@Dao
interface WordDao {

    @Transaction
    @Query("SELECT * FROM WordDBO")
    suspend fun getAllWords() : List<WordWithAllAttributes>?

    @Transaction
    @Query("SELECT * FROM WordDBO WHERE word = :word")
    suspend fun getWordWithMeanings(word: String): WordWithAllAttributes?

    @Query("SELECT COUNT(*) FROM WordDBO")
    suspend fun getWordsCount(): Int

    @Query("DELETE FROM WordDBO WHERE word = :word")
    suspend fun deleteWord(word: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(wordDBO: WordDBO)

}