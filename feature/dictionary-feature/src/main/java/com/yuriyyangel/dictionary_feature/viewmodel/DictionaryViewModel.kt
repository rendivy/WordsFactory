package com.yuriyyangel.dictionary_feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yangel.dictionary_data.model.WordDTO
import ru.yangel.dictionary_data.repository.DictionaryRepository
import javax.inject.Inject


sealed class DictionaryState {
    data object Initial : DictionaryState()
    data object Loading : DictionaryState()
    data class Success(val data: List<WordDTO>) : DictionaryState()
    data object Error : DictionaryState()
}


@HiltViewModel
class DictionaryViewModel @Inject constructor(private val dictionaryRepository: DictionaryRepository) :
    ViewModel() {


    private val _dictionaryState = MutableStateFlow<DictionaryState>(DictionaryState.Initial)
    val dictionaryState = _dictionaryState.asStateFlow()


    fun getWordWithDefinition(word: String = "war") {
        viewModelScope.launch {
            _dictionaryState.value = DictionaryState.Loading
            try {
                val data = dictionaryRepository.getWordWithDefinition(word)
                _dictionaryState.value = DictionaryState.Success(data)
            } catch (e: Exception) {
                _dictionaryState.value = DictionaryState.Error
            }
        }
    }

}