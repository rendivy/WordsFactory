package ru.yangel.dictionary_data.model

data class QuestionDTO(
    val question: String,
    val answers: List<String>,
    val correctAnswer: String,
    var isCorrect: Boolean = false
)