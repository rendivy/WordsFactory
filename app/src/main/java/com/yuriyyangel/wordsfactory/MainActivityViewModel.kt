package com.yuriyyangel.wordsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriyyangel.wordsfactory.data.StorageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(storageRepository: StorageRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(false)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            storageRepository.isTrainingPassed().collect { value ->
                _state.value = value
            }
        }
    }

}