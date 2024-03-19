package com.yuriyyangel.dictionaryapi.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Phonetic(
    @SerialName("audio")
    val audio: String,
    @SerialName("text")
    val text: String
)