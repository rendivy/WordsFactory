package com.keyinc.words.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DefinitionDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val wordId: String,
    val example: String,
    val definition: String,
)

