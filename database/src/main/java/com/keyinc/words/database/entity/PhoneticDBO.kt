package com.keyinc.words.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["audio", "text"], unique = true)])
data class PhoneticDBO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val wordId: String,
    val audio: String,
    val text: String
)