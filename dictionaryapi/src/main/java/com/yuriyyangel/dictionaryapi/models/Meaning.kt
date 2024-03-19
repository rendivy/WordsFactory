package com.yuriyyangel.dictionaryapi.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meaning(
    @SerialName("definitions")
    val definitions: List<Definition>,
    @SerialName("partOfSpeech")
    val partOfSpeech: String
)