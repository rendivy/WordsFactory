package ru.yangel.dictionary_data.mappers

import ru.yangel.dictionary_data.model.QuestionDTO
import ru.yangel.dictionary_data.model.WordDTO

object QuestionMapper {

    fun map(wordDTO: WordDTO, answers: List<String>): QuestionDTO {
        return QuestionDTO(
            question = wordDTO.meanings[0].definitions.firstNotNullOf { it.definition },
            answers = answers,
            correctAnswer = wordDTO.word
        )
    }
}