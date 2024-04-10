package com.keyinc.words.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"])])
data class DefinitionDBO(
    @PrimaryKey
    val id: String,
    val example: String,
    val definition: String,
)

