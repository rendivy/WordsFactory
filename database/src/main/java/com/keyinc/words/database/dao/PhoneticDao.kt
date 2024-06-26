package com.keyinc.words.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keyinc.words.database.entity.PhoneticDBO


@Dao
interface PhoneticDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhonetic(phoneticDBO: PhoneticDBO)

    @Query("DELETE FROM PhoneticDBO WHERE wordId = :word")
    suspend fun deletePhoneticOfWord(word: String)
}