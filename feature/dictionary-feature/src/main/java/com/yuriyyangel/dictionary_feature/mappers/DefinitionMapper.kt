package com.yuriyyangel.dictionary_feature.mappers

import com.yuriyyangel.dictionary_feature.model.DefinitionPresentation
import ru.yangel.core.mappers.Mapper
import ru.yangel.dictionary_data.model.DefinitionDTO

class DefinitionMapper : Mapper<DefinitionPresentation, DefinitionDTO> {
    override fun transform(data: DefinitionDTO): DefinitionPresentation {
        return DefinitionPresentation(
            definition = data.definition,
            example = data.example,
            synonyms = data.synonyms
        )
    }

}