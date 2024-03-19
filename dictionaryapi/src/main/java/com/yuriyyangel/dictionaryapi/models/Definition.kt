package com.yuriyyangel.dictionaryapi.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Definition(
    @SerialName("antonyms")
    val antonyms: List<String>,
    @SerialName("definition")
    val definition: String,
    @SerialName("example")
    val example: String,
    @SerialName("synonyms")
    val synonyms: List<String>
)