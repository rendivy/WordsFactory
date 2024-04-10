package com.yuriyyangel.dictionary_feature.model


data class WordPresentation(
    val meanings: List<MeaningPresentation>,
    val origin: String?,
    val phonetic: String,
    val phonetics: List<PhoneticPresentation>,
    val word: String
)

data class MeaningPresentation(
    val definitions: List<DefinitionPresentation>,
    val partOfSpeech: String
)

data class PhoneticPresentation(
    val audio: String,
    val text: String,
)

data class DefinitionPresentation(
    val definition: String?,
    val example: String?,
    val synonyms: List<String>?
)

