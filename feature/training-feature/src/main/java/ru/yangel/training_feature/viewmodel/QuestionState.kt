package ru.yangel.training_feature.viewmodel

sealed class QuestionState {
    data object Initial : QuestionState()
    data object WordClicked : QuestionState()
    data object Question : QuestionState()
    data class QuestionAnswered(val correct: Int, val incorrect: Int) : QuestionState()
}