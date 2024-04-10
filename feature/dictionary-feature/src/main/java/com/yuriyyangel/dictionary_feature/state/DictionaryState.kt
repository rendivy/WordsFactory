package com.yuriyyangel.dictionary_feature.state

import ru.yangel.dictionary_data.model.WordDTO

sealed class DictionaryState {
    data object Initial : DictionaryState()
    data object Loading : DictionaryState()
    data class Success(val data: List<WordDTO>) : DictionaryState()
    data class Error(val errorMessage: DictionaryError) : DictionaryState()
}


enum class DictionaryError {
    WORD_NOT_FOUND,
    UNEXPECTED_EXCEPTION
}