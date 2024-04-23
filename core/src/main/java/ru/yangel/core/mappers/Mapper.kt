package ru.yangel.core.mappers

interface Mapper<SRC, DST>  {
    fun transform(data: DST) : SRC
}