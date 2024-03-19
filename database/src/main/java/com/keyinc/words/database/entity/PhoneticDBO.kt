package com.keyinc.words.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = [Index(value = ["id"], unique = true)])
data class PhoneticDBO(
    @PrimaryKey
    val id: String,
    val audio: String,
    val text: String
)