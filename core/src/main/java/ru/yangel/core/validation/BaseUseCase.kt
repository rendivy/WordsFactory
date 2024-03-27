package ru.yangel.core.validation

interface BaseUseCase<Input, Output> {
    fun execute(param: Input): Output
}