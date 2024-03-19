package com.keyinc.words.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.keyinc.words.database.entity.PhoneticDBO


@Dao
interface PhoneticDao {

    @Insert
    suspend fun insertPhonetic(phoneticDBO: PhoneticDBO)

    @Query("DELETE FROM PhoneticDBO WHERE id = :word")
    suspend fun deletePhoneticOfWord(word: String)
}