package com.yuriyyangel.dictionary_feature.mappers

import com.yuriyyangel.dictionary_feature.model.PhoneticPresentation
import ru.yangel.core.mappers.Mapper
import ru.yangel.dictionary_data.model.PhoneticDTO

class PhoneticMapper : Mapper<PhoneticPresentation, PhoneticDTO> {
    override fun transform(data: PhoneticDTO): PhoneticPresentation {
        return PhoneticPresentation(audio = data.audio, text = data.text)
    }
}