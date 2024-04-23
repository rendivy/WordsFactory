package ru.yangel.dictionary_data.model

data class DefinitionDTO(
    val definition: String?,
    val example: String?,
    val synonyms: List<String>?
)