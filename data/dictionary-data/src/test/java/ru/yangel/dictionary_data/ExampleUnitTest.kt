package ru.yangel.dictionary_data

import com.keyinc.words.database.entity.DefinitionDBO
import com.keyinc.words.database.entity.MeaningDBO
import com.keyinc.words.database.entity.PhoneticDBO
import com.keyinc.words.database.entity.WordDBO
import com.keyinc.words.database.entity.WordWithAllAttributes
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import ru.yangel.dictionary_data.model.QuestionDTO
import ru.yangel.dictionary_data.model.WordDTO
import ru.yangel.dictionary_data.repository.QuestionRepositoryImpl
import ru.yangel.dictionary_data.repository.WordRepositoryImpl

class QuestionRepositoryImplTest {

    private val mockLocalDataSource = mock(DictionaryLocalDataSource::class.java)
    private val mockWordRepository = mock(WordRepositoryImpl::class.java)
    private val repository = QuestionRepositoryImpl(mockLocalDataSource, mockWordRepository)

    @Test
    fun `GIVEN a question WHEN setSkillRatio is called THEN skillRatio is incremented`(): Unit =
        runBlocking {
            val question = createQuestionDTO()
            val wordWithMeaning = createWordDBO()

            Mockito.`when`(mockLocalDataSource.getWord(question.correctAnswer))
                .thenReturn(wordWithMeaning)

            repository.setSkillRation(question, isCorrect = true)

            val expectedSkillRatio = wordWithMeaning.skillRatio + 1
            val expectedWordWithMeaning = wordWithMeaning.copy(skillRatio = expectedSkillRatio)
            Mockito.verify(mockLocalDataSource).updateWordRatio(expectedWordWithMeaning)
        }


    @Test
    fun `GIVEN a question WHEN setSkillRatio is called THEN skillRatio is decrement`(): Unit =
        runBlocking {
            val question = createQuestionDTO()
            val wordWithMeaning = createWordDBO()

            Mockito.`when`(mockLocalDataSource.getWord(question.correctAnswer))
                .thenReturn(wordWithMeaning)

            repository.setSkillRation(question, isCorrect = false)

            val expectedSkillRatio = wordWithMeaning.skillRatio - 1
            val expectedWordWithMeaning = wordWithMeaning.copy(skillRatio = expectedSkillRatio)
            Mockito.verify(mockLocalDataSource).updateWordRatio(expectedWordWithMeaning)
        }


    @Test
    fun `WHEN getQuestions is called THEN returns expected questions`(): Unit = runBlocking {

        val wordsWithLowSkillRatio = listOf(
            createWordWithAllAttributes(),
            createWordWithAllAttributes(),
            createWordWithAllAttributes(),
        )
        Mockito.`when`(mockLocalDataSource.getWordsWithLowSkillRatio())
            .thenReturn(wordsWithLowSkillRatio)

        val allWords = listOf(
            createWordDTO(),
            createWordDTO(),
            createWordDTO(),
            createWordDTO()
        )
        Mockito.`when`(mockWordRepository.getAllWord())
            .thenReturn(allWords)

        val result = repository.getQuestions()

        Mockito.verify(mockLocalDataSource).getWordsWithLowSkillRatio()
        assertTrue(result.all { question ->
            wordsWithLowSkillRatio.any { it.wordDBO.word == question.correctAnswer }
        })

    }


    @Test
    fun `WHEN getQuestions is called AND getWordsWithLowSkillRatio returns empty list THEN returns empty list`(): Unit =
        runBlocking {

            val wordsWithLowSkillRatio = emptyList<WordWithAllAttributes>()
            Mockito.`when`(mockLocalDataSource.getWordsWithLowSkillRatio())
                .thenReturn(wordsWithLowSkillRatio)

            val result = repository.getQuestions()

            Mockito.verify(mockLocalDataSource).getWordsWithLowSkillRatio()
            assertTrue(result.isEmpty())
        }

    private fun createWordWithAllAttributes() = WordWithAllAttributes(
        wordDBO = WordDBO(
            word = "word",
            phonetic = "phonetic",
            skillRatio = 0
        ),
        meaningDBOS = createMeaningDBOS(),
        phoneticDBOS = createPhoneticDBOS(),
        definitionDBOS = createDefinitionDBOS()
    )

    private fun createWordDTO() = WordDTO(
        word = "word",
        phonetic = "phonetic",
        phonetics = emptyList(),
        meanings = emptyList(),
        origin = null
    )

    private fun createMeaningDBOS() = listOf(
        MeaningDBO(
            id = 1,
            wordId = "word1",
            partOfSpeech = "noun"
        ),
        MeaningDBO(
            id = 2,
            wordId = "word2",
            partOfSpeech = "verb"
        )
    )

    private fun createPhoneticDBOS() = listOf(
        PhoneticDBO(
            id = 1,
            wordId = "word1",
            text = "/ˈwɜːrd/",
            audio = "https://audio.example.com/w1.mp3"
        ),
        PhoneticDBO(
            id = 2,
            wordId = "word2",
            text = "/ˈwɜːrd2/",
            audio = "https://audio.example.com/w2.mp3"
        )
    )

    private fun createDefinitionDBOS() = listOf(
        DefinitionDBO(
            id = 1,
            wordId = "word1",
            definition = "A single distinct meaningful element of speech or writing.",
            example = "The word 'run' has over 50 different meanings."
        ),
        DefinitionDBO(
            id = 2,
            wordId = "word2",
            definition = "A second example definition.",
            example = "An example usage of the second word."
        )
    )

    private fun createQuestionDTO(): QuestionDTO {
        return QuestionDTO(
            question = "question",
            answers = listOf("answer1", "answer2", "answer3"),
            correctAnswer = "answer1"
        )
    }

    private fun createWordDBO() = WordDBO(
        word = "word",
        phonetic = "phonetic",
        skillRatio = 0
    )
}