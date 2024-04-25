package com.yuriyyangel.dictionary_feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriyyangel.dictionary_feature.services.MediaPlayerService
import com.yuriyyangel.dictionary_feature.state.DictionaryError
import com.yuriyyangel.dictionary_feature.state.DictionaryState
import com.yuriyyangel.dictionary_feature.state.DictionaryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.yangel.core.di.AppDispatchers
import ru.yangel.dictionary_data.model.WordDTO
import ru.yangel.dictionary_data.repository.DictionaryRepository
import ru.yangel.dictionary_data.repository.WordRepository
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    private val dictionaryRepository: DictionaryRepository,
    private val wordRepository: WordRepository,
    private val appDispatchers: AppDispatchers,
) : ViewModel() {


    private val _dictionaryState =
        MutableStateFlow<DictionaryState>(DictionaryState.Initial)
    val dictionaryState = _dictionaryState.asStateFlow()

    private val _dictionaryUiState = MutableStateFlow(DictionaryUiState())
    val dictionaryUiState = _dictionaryUiState.asStateFlow()


    fun getWordWithDefinition() {
        if (_dictionaryUiState.value.word.isNotBlank()) {
            _dictionaryState.value = DictionaryState.Loading
            viewModelScope.launch(appDispatchers.io) {
                val localWord = wordRepository.getWordFromLocalSource(_dictionaryUiState.value.word)
                if (localWord != null) {
                    _dictionaryState.value = DictionaryState.Success(listOf(localWord))
                    return@launch
                } else {
                    try {
                        _dictionaryState.value = DictionaryState.Success(
                            dictionaryRepository.getWordWithDefinition(_dictionaryUiState.value.word)
                        )
                    } catch (e: Exception) {
                        _dictionaryState.value =
                            DictionaryState.Error(DictionaryError.WORD_NOT_FOUND)
                    }
                }
            }
        }
    }

    fun saveWord(wordDto: WordDTO) {
        viewModelScope.launch(appDispatchers.io) {
            wordRepository.saveWord(wordDTO = wordDto)
        }
    }

    fun resetState() {
        viewModelScope.launch(appDispatchers.io) {
            _dictionaryState.value = DictionaryState.Initial
        }
    }

    fun playAudio(url: String, onCompleteListener: () -> Unit, onStartListener: () -> Unit) {
        viewModelScope.launch {
            try  {
                withContext(Dispatchers.IO) {
                    val mediaPlayer = MediaPlayerService(
                        url = url,
                        onCompleteListener = onCompleteListener,
                        onStartListener = onStartListener
                    )
                    mediaPlayer.mediaPlayer.start()
                }
            }
            catch (e: Exception) {
                _dictionaryState.value =
                    DictionaryState.Error(DictionaryError.UNEXPECTED_EXCEPTION)
            }
        }
    }

    fun onChangeWord(word: String) {
        _dictionaryUiState.value = _dictionaryUiState.value.copy(
            word = word
        )
    }

}