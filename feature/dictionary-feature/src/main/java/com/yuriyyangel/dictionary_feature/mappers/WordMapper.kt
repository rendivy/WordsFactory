package com.yuriyyangel.dictionary_feature.mappers

import com.yuriyyangel.dictionary_feature.model.DefinitionPresentation
import com.yuriyyangel.dictionary_feature.model.MeaningPresentation
import com.yuriyyangel.dictionary_feature.model.PhoneticPresentation
import com.yuriyyangel.dictionary_feature.model.WordPresentation
import ru.yangel.core.mappers.Mapper
import ru.yangel.dictionary_data.model.DefinitionDTO
import ru.yangel.dictionary_data.model.MeaningDTO
import ru.yangel.dictionary_data.model.PhoneticDTO
import ru.yangel.dictionary_data.model.WordDTO

class WordMapper : Mapper<WordPresentation, WordDTO> {

    override fun transform(data: WordDTO): WordPresentation {
        return WordPresentation(
            origin = data.origin,
            phonetic = data.phonetic,
            phonetics = data.phonetics.map { sourceModel ->
                PhoneticPresentation().transform(
                    sourceModel
                )
            },
            word = data.word,
            meanings = data.meanings.map { sourceModel ->
                MeaningMapper().transform(
                    sourceModel
                )
            }

        )
    }
}

class PhoneticPresentation : Mapper<PhoneticPresentation, PhoneticDTO> {
    override fun transform(data: PhoneticDTO): PhoneticPresentation {
        return PhoneticPresentation(audio = data.audio, text = data.text)
    }
}


class DefinitionMapper : Mapper<DefinitionPresentation, DefinitionDTO> {
    override fun transform(data: DefinitionDTO): DefinitionPresentation {
        return DefinitionPresentation(
            definition = data.definition,
            example = data.example,
            synonyms = data.synonyms
        )
    }

}

class MeaningMapper : Mapper<MeaningPresentation, MeaningDTO> {
    override fun transform(data: MeaningDTO): MeaningPresentation {
        return MeaningPresentation(
            partOfSpeech = data.partOfSpeech,
            definitions = data.definitions.map { sourceModel ->
                DefinitionMapper().transform(
                    sourceModel
                )
            }
        )
    }

}