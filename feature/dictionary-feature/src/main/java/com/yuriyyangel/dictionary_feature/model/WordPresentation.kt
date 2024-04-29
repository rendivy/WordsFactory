package com.yuriyyangel.dictionary_feature.model


data class WordPresentation(
    val meanings: List<MeaningPresentation>,
    val origin: String?,
    val phonetic: String,
    val phonetics: List<PhoneticPresentation>,
    val word: String
)

