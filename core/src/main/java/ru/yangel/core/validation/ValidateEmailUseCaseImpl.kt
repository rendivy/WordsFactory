package ru.yangel.core.validation

import ru.yangel.core.usecase.ValidateEmailUseCase

class ValidateEmailUseCaseImpl : ValidateEmailUseCase {

    override fun execute(param: String): ValidationResult {

        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
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