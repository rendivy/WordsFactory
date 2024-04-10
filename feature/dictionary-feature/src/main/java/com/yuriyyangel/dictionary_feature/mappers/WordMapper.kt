package com.yuriyyangel.dictionary_feature.mappers

import com.yuriyyangel.dictionary_feature.model.WordPresentation
import ru.yangel.core.mappers.Mapper
import ru.yangel.dictionary_data.model.WordDTO

class WordMapper : Mapper<WordPresentation, WordDTO> {

    override fun transform(data: WordDTO): WordPresentation {
        return WordPresentation(
            origin = data.origin,
            phonetic = data.phonetic,
            phonetics = data.phonetics.map { sourceModel ->
                PhoneticMapper().transform(
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


