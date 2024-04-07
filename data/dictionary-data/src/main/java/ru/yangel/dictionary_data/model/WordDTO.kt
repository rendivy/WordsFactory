package ru.yangel.dictionary_data.model

data class WordDTO(
    val meanings: List<MeaningDTO>,
    val origin: String,
    val phonetic: String,
    val phonetics: List<PhoneticDTO>,
    val word: String
)

data class MeaningDTO(
    val definitions: List<DefinitionDTO>,
    val partOfSpeech: String
)


data class PhoneticDTO(
    val audio: String,
    val text: String,
)

data class DefinitionDTO(
    val definition: String,
    val example: String,
    val synonyms: List<String>
)

