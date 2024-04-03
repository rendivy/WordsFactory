package ru.yangel.core.validation

import ru.yangel.core.usecase.ValidateNameUseCase

class ValidateNameUseCaseImpl : ValidateNameUseCase {

    override fun execute(param: String): ValidationResult {
        return if (param.isBlank())
            ValidationResult(
                isSuccessful = false,
                errorType = ErrorType.EMAIL_ERROR
            )
        else
            ValidationResult(
                isSuccessful = true,
                errorType = null
            )
    }
}


fun ValidateNameUseCase(): ValidateNameUseCase = ValidateNameUseCaseImpl()