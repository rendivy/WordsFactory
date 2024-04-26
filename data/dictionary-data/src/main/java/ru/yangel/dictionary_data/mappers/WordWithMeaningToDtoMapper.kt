package ru.yangel.dictionary_data.mappers

import com.keyinc.words.database.entity.DefinitionDBO
import com.keyinc.words.database.entity.MeaningDBO
import com.keyinc.words.database.entity.PhoneticDBO
import com.keyinc.words.database.entity.WordWithAllAttributes
import ru.yangel.core.mappers.Mapper
import ru.yangel.dictionary_data.model.DefinitionDTO
import ru.yangel.dictionary_data.model.MeaningDTO
import ru.yangel.dictionary_data.model.PhoneticDTO
import ru.yangel.dictionary_data.model.WordDTO

class WordWithMeaningToDtoMapper : Mapper<WordDTO, WordWithAllAttributes> {

    override fun transform(data: WordWithAllAttributes): WordDTO {
        return WordDTO(
            word = data.wordDBO.word,
            phonetic = data.wordDBO.phonetic,
            phonetics = data.phoneticDBOS.map { MapperHelper.transformPhonetic(it) },
            meanings = data.meaningDBOS.map {
                MapperHelper.transformMeaning(
                    it,
                    data.definitionDBOS
                )
            },
            origin = null,
        )
    }

}

object MapperHelper {
    fun transformPhonetic(data: PhoneticDBO): PhoneticDTO {
        return PhoneticDTO(
            text = data.text,
            audio = data.audio,
        )
    }

    fun transformMeaning(data: MeaningDBO, definitionDBO: List<DefinitionDBO>): MeaningDTO {
        return MeaningDTO(
            partOfSpeech = data.partOfSpeech,
            definitions = definitionDBO.map { transformDefinition(it) }
        )
    }

    fun transformDefinition(data: DefinitionDBO): DefinitionDTO {
        return DefinitionDTO(
            definition = data.definition,
            example = data.example,
            synonyms = null,
        )
    }
}