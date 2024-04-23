package ru.yangel.dictionary_data.model

data class WordDTO(
    val meanings: List<MeaningDTO>,
    val origin: String?,
    val phonetic: String,
    val phonetics: List<PhoneticDTO>,
    val word: String
)


