package com.keyinc.words.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.keyinc.words.database.entity.DefinitionDBO

@Dao
interface DefinitionDao {
    @Insert
    suspend fun insertDefinition(definitionDBO: DefinitionDBO)

    @Query("DELETE FROM DefinitionDBO WHERE id = :word")
    suspend fun deleteDefinitionsOfWord(word: String)
}