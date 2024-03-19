package com.keyinc.words.database.entity

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(indices = [Index(value = ["id"], unique = true)])
data class DefinitionDBO(
    @PrimaryKey
    val id: String,
    val example: String,
    val definition: String,
)

@DatabaseView(
    """
    SELECT MeaningDBO.*, PhoneticDBO.*, DefinitionDBO.*
    FROM MeaningDBO
    INNER JOIN PhoneticDBO ON MeaningDBO.id = PhoneticDBO.id
    INNER JOIN DefinitionDBO ON MeaningDBO.id = DefinitionDBO.id
    """
)
data class WordWithAllAttributes(
    @Embedded val wordDBO: WordDBO,
    @Relation(parentColumn = "word", entityColumn = "id")
    val meaningDBOS: List<MeaningDBO>,
    @Relation(parentColumn = "word", entityColumn = "id")
    val phoneticDBOS: List<PhoneticDBO>,
    @Relation(parentColumn = "word", entityColumn = "id")
    val definitionDBOS: List<DefinitionDBO>,
)