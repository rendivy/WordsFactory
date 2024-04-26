package ru.yangel.training_feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yangel.core.di.AppDispatchers
import ru.yangel.dictionary_data.repository.WordRepository
import javax.inject.Inject


sealed class TrainingState {
    data object Initial : TrainingState()
    data object Start : TrainingState()
}


@HiltViewModel
class TrainingViewModel @Inject constructor(
    private val wordRepository: WordRepository,
    private val appDispatcher: AppDispatchers
) : ViewModel() {

    init {
        viewModelScope.launch(appDispatcher.io) {
            val wordCount = wordRepository.getWordsCount()
            _wordCount.value = wordCount
        }
    }

    private val _trainingState = MutableStateFlow<TrainingState>(TrainingState.Initial)
    val trainingState = _trainingState.asStateFlow()

    private val _wordCount = MutableStateFlow(0)
    val wordCount = _wordCount.asStateFlow()


    fun startTraining() {
        _trainingState.value = TrainingState.Start
    }

    fun startQuestion() {
        // navigate to training
    }

}