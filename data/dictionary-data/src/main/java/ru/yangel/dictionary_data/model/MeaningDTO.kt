package ru.yangel.dictionary_data.model

data class MeaningDTO(
    val definitions: List<DefinitionDTO>,
    val partOfSpeech: String
)