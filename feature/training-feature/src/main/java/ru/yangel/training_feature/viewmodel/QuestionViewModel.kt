package ru.yangel.training_feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.yangel.auth_data.storage.repository.AuthStorageRepository
import ru.yangel.core.di.AppDispatchers
import ru.yangel.dictionary_data.model.QuestionDTO
import ru.yangel.dictionary_data.repository.QuestionRepository
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val authRepository: AuthStorageRepository,
    private val appDispatchers: AppDispatchers
) : ViewModel() {


    private val _questionState = MutableStateFlow<QuestionState>(QuestionState.Initial)
    val questionState = _questionState.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex = _currentQuestionIndex.asStateFlow()

    private val _questionList = MutableStateFlow<MutableList<QuestionDTO>>(mutableListOf())
    val questionList = _questionList.asStateFlow()


    fun resetToQuestion() {
        _questionState.value = QuestionState.Question
    }

    fun resetState() {
        _questionState.value = QuestionState.Initial
    }

    fun onInitial() {
        viewModelScope.launch(appDispatchers.io) {
            val localQuestionList = questionRepository.getQuestions()
            questionList.value.clear()
            questionList.value.addAll(localQuestionList)
            _questionState.value = QuestionState.Question
            _currentQuestionIndex.value = 0
        }
    }

    fun onAnswerSelected(word: String) {
        viewModelScope.launch {
            delay(500)
            val currentQuestion = questionList.value[currentQuestionIndex.value]
            if (currentQuestion.correctAnswer == word) {
                questionList.value[currentQuestionIndex.value].isCorrect = true
                questionRepository.setSkillRation(currentQuestion, true)
            } else {
                questionList.value[currentQuestionIndex.value].isCorrect = false
                questionRepository.setSkillRation(currentQuestion, false)
            }
            _currentQuestionIndex.value++
            if (_currentQuestionIndex.value == questionList.value.size) {
                _questionState.value = QuestionState.QuestionAnswered(
                    questionList.value.count { it.isCorrect },
                    questionList.value.count { !it.isCorrect }
                )
            } else {
                _questionState.value = QuestionState.WordClicked
            }
            authRepository.setTrainingPassed(true)
        }
    }
}