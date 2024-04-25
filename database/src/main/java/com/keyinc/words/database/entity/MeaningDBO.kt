package com.keyinc.words.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["partOfSpeech"], unique = true)])
data class MeaningDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val wordId: String,
    val partOfSpeech: String,
)


