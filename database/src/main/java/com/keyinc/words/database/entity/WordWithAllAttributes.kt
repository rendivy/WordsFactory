package com.keyinc.words.database.entity

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation

@DatabaseView(
    """
    SELECT MeaningDBO.*, PhoneticDBO.*, DefinitionDBO.*
    FROM MeaningDBO
    INNER JOIN PhoneticDBO ON MeaningDBO.wordId = PhoneticDBO.wordId
    INNER JOIN DefinitionDBO ON MeaningDBO.wordId = DefinitionDBO.wordId
    """
)
data class WordWithAllAttributes(
    @Embedded val wordDBO: WordDBO,
    @Relation(parentColumn = "word", entityColumn = "wordId")
    val meaningDBOS: List<MeaningDBO>,
    @Relation(parentColumn = "word", entityColumn = "wordId")
    val phoneticDBOS: List<PhoneticDBO>,
    @Relation(parentColumn = "word", entityColumn = "wordId")
    val definitionDBOS: List<DefinitionDBO>,
)