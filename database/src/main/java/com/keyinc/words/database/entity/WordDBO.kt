package com.keyinc.words.database.entity


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["word"], unique = true)])
data class WordDBO(
    @PrimaryKey
    val word: String,
    val phonetic: String,
)





