package com.yuriyyangel.dictionary_feature.mappers

import com.yuriyyangel.dictionary_feature.model.MeaningPresentation
import ru.yangel.core.mappers.Mapper
import ru.yangel.dictionary_data.model.MeaningDTO

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