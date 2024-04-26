package com.keyinc.words.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keyinc.words.database.entity.MeaningDBO

@Dao
interface MeaningDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeaning(meaningDBO: MeaningDBO)

    @Query("DELETE FROM MeaningDBO WHERE wordId = :word")
    suspend fun deleteMeaningsOfWord(word: String)
}