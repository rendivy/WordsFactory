package ru.yangel.dictionary_data.mappers

import com.keyinc.words.database.entity.DefinitionDBO
import com.keyinc.words.database.entity.MeaningDBO
import com.keyinc.words.database.entity.PhoneticDBO
import com.keyinc.words.database.entity.WordDBO
import ru.yangel.core.mappers.Mapper
import ru.yangel.dictionary_data.model.DefinitionDTO
import ru.yangel.dictionary_data.model.MeaningDTO
import ru.yangel.dictionary_data.model.PhoneticDTO
import ru.yangel.dictionary_data.model.WordDTO

class WordToEntityMapper : Mapper<WordDBO, WordDTO> {
    override fun transform(data: WordDTO): WordDBO {
        return WordDBO(
            phonetic = data.phonetic,
            word = data.word
        )
    }

    fun transformDefinition(
        data: WordDTO,
        definitionList: List<DefinitionDTO>
    ): List<DefinitionDBO> {
        return definitionList.map {
            DefinitionDBO(
                id = data.word,
                definition = it.definition ?: "",
                example = it.example ?: "",
            )
        }
    }

    fun transformMeaning(data: WordDTO, meaningList: List<MeaningDTO>): List<MeaningDBO> {
        return meaningList.map {
            MeaningDBO(
                id = data.word,
                partOfSpeech = it.partOfSpeech ?: "",
            )
        }
    }


    fun transformPhonetic(data: WordDTO, phoneticList: List<PhoneticDTO>): List<PhoneticDBO> {
        return phoneticList.map {
            PhoneticDBO(
                id = data.word,
                audio = it.audio,
                text = it.text,
            )
        }
    }


}
