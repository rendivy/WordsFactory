package ru.yangel.dictionary_data.mappers

import com.yuriyyangel.dictionaryapi.models.Definition
import com.yuriyyangel.dictionaryapi.models.Meaning
import com.yuriyyangel.dictionaryapi.models.Phonetic
import com.yuriyyangel.dictionaryapi.models.WordResponseItem
import ru.yangel.dictionary_data.model.DefinitionDTO
import ru.yangel.dictionary_data.model.MeaningDTO
import ru.yangel.dictionary_data.model.PhoneticDTO
import ru.yangel.dictionary_data.model.WordDTO

internal object WordMapper {

    fun mapWordResponseItemToWord(wordResponseItem: WordResponseItem): WordDTO {
        return WordDTO(
            meanings = wordResponseItem.meanings.map { mapMeaningToMeaningDTO(it) },
            origin = wordResponseItem.origin,
            phonetic = wordResponseItem.phonetic,
            phonetics = wordResponseItem.phonetics.map { mapPhoneticToPhoneticDTO(it) },
            word = wordResponseItem.word
        )
    }

    private fun mapMeaningToMeaningDTO(meaning: Meaning): MeaningDTO {
        return MeaningDTO(
            definitions = meaning.definitions.map { mapDefinitionToDefinitionDTO(it) },
            partOfSpeech = meaning.partOfSpeech
        )
    }

    private fun mapPhoneticToPhoneticDTO(phonetic: Phonetic): PhoneticDTO {
        return PhoneticDTO(
            audio = phonetic.audio,
            text = phonetic.text
        )
    }

    private fun mapDefinitionToDefinitionDTO(definition: Definition): DefinitionDTO {
        return DefinitionDTO(
            definition = definition.definition,
            example = definition.example,
            synonyms = definition.synonyms
        )
    }
}