package com.yuriyyangel.dictionaryapi.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordResponseItem(
    @SerialName("meanings")
    val meanings: List<Meaning>,
    @SerialName("origin")
    val origin: String,
    @SerialName("phonetic")
    val phonetic: String,
    @SerialName("phonetics")
    val phonetics: List<Phonetic>,
    @SerialName("word")
    val word: String
)