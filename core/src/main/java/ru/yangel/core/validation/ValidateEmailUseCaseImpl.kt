package ru.yangel.core.validation

import ru.yangel.core.usecase.ValidateEmailUseCase

class ValidateEmailUseCaseImpl : ValidateEmailUseCase {

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


fun ValidateEmailUseCase(): ValidateEmailUseCase {
    return ValidateEmailUseCaseImpl()
}