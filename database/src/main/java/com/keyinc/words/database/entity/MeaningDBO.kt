package com.keyinc.words.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["id"])])
data class MeaningDBO(
    @PrimaryKey
    val id: String,
    val partOfSpeech: String,
)


