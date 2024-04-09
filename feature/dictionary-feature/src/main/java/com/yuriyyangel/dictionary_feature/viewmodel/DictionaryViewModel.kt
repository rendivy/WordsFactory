package com.yuriyyangel.dictionary_feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriyyangel.dictionary_feature.services.MediaPlayerService
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


data class DictionaryUiState(
    val word: String = ""
)


@HiltViewModel
class DictionaryViewModel @Inject constructor(private val dictionaryRepository: DictionaryRepository) :
    ViewModel() {

    private val _dictionaryState = MutableStateFlow<DictionaryState>(DictionaryState.Initial)
    val dictionaryState = _dictionaryState.asStateFlow()

    private val _dictionaryUiState = MutableStateFlow(DictionaryUiState())
    val dictionaryUiState = _dictionaryUiState.asStateFlow()


    fun getWordWithDefinition() {
        if (_dictionaryUiState.value.word.isNotBlank()) {
            viewModelScope.launch {
                _dictionaryState.value = DictionaryState.Loading
                val data = dictionaryRepository.getWordWithDefinition(_dictionaryUiState.value.word)
                _dictionaryState.value = DictionaryState.Success(data)
            }
        }
    }



    fun playAudio(url: String, onCompleteListener: () -> Unit, onStartListener: () -> Unit) {
        val mediaPlayer = MediaPlayerService(
            url = url,
            onCompleteListener = onCompleteListener,
            onStartListener = onStartListener
        )
        mediaPlayer.mediaPlayer.start()
    }

    fun onChangeWord(word: String) {
        _dictionaryUiState.value = _dictionaryUiState.value.copy(
            word = word
        )
    }

}