package com.yuriyyangel.dictionary_feature.state

import com.yuriyyangel.dictionary_feature.model.WordPresentation

sealed class DictionaryState {
    data object Initial : DictionaryState()
    data object Loading : DictionaryState()
    data class Success(val data: List<WordPresentation>) : DictionaryState()
    data class Error(val errorMessage: DictionaryError) : DictionaryState()
}


enum class DictionaryError {
    WORD_NOT_FOUND,
    UNEXPECTED_EXCEPTION
}