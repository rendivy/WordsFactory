package com.yuriyyangel.dictionary_feature.state

import ru.yangel.dictionary_data.model.WordDTO

sealed class DictionaryState {
    data object Initial : DictionaryState()
    data object Loading : DictionaryState()
    data class Success(val data: List<WordDTO>) : DictionaryState()
    data object Error : DictionaryState()
}
