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
    suspend fun getAllWords(): List<WordWithAllAttributes>?

    @Transaction
    @Query("SELECT * FROM WordDBO WHERE skillRatio < 5")
    suspend fun getWordsWithLowSkillRatio(): List<WordWithAllAttributes>?

    @Query("SELECT COUNT(*) FROM WordDBO WHERE skillRatio >= 5")
    suspend fun getRememberedWordsCount() : Int

    @Transaction
    @Query("SELECT * FROM WordDBO WHERE word = :word")
    suspend fun getWordWithMeanings(word: String): WordWithAllAttributes?

    @Query("SELECT COUNT(*) FROM WordDBO")
    suspend fun getWordsCount(): Int

    @Query("SELECT * FROM WordDBO WHERE word = :word")
    suspend fun getWord(word: String): WordDBO?

    @Query("DELETE FROM WordDBO WHERE word = :word")
    suspend fun deleteWord(word: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(wordDBO: WordDBO)

}