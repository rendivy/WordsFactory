package ru.yangel.dictionary_data.repository

import ru.yangel.dictionary_data.model.QuestionDTO

interface QuestionRepository {
    suspend fun getQuestions(): List<QuestionDTO>
    suspend fun setSkillRation(word: QuestionDTO, isCorrect: Boolean)
}