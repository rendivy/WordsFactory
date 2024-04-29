package ru.yangel.dictionary_data.repository

import ru.yangel.dictionary_data.DictionaryLocalDataSource
import ru.yangel.dictionary_data.mappers.QuestionMapper
import ru.yangel.dictionary_data.mappers.WordWithMeaningToDtoMapper
import ru.yangel.dictionary_data.model.QuestionDTO
import javax.inject.Inject

internal class QuestionRepositoryImpl @Inject constructor(
    private val localDataSource: DictionaryLocalDataSource,
    private val wordRepository: WordRepository
) : QuestionRepository {
    private val wordWithMeaningToDtoMapper = WordWithMeaningToDtoMapper()

    override suspend fun getQuestions(): List<QuestionDTO> {
        val words = localDataSource.getWordsWithLowSkillRatio()
            ?.shuffled()
            ?.map { wordWithMeaningToDtoMapper.transform(it) } ?: emptyList()

        val numberOfQuestions = if (words.size >= 10) 10 else words.size
        return words.take(numberOfQuestions).map { wordDTO ->
            val answers = wordRepository.getAllWord()
                .filter { it.word != wordDTO.word }
                .shuffled()
                .take(2)
                .map { it.word }
                .plus(wordDTO.word)
                .shuffled()
            QuestionMapper.map(wordDTO, answers)
        }
    }

    override suspend fun setSkillRation(word: QuestionDTO, isCorrect: Boolean) {
        val wordWithMeaning = localDataSource.getWord(word.correctAnswer)
        if (wordWithMeaning != null) {
            val newSkillRatio = if (isCorrect) {
                wordWithMeaning.skillRatio + 1
            } else {
                wordWithMeaning.skillRatio - 1
            }
            localDataSource.updateWordRatio(wordWithMeaning.copy(skillRatio = newSkillRatio))
        }
    }


}