package com.keyinc.words.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.keyinc.words.database.entity.DefinitionDBO

@Dao
interface DefinitionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDefinition(definitionDBO: DefinitionDBO)

    @Query("DELETE FROM DefinitionDBO WHERE wordId = :word")
    suspend fun deleteDefinitionsOfWord(word: String)
}