package ru.yangel.core.validation

import ru.yangel.core.usecase.ValidatePasswordUseCase
import javax.inject.Inject

class ValidatePasswordUseCaseImpl @Inject constructor() : ValidatePasswordUseCase {

    private companion object {
        private const val MIN_PASSWORD_LENGTH = 6
    }

    override fun execute(param: String): ValidationResult {
        return if (param.length < MIN_PASSWORD_LENGTH)
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


fun ValidatePasswordUseCase(): ValidatePasswordUseCase = ValidatePasswordUseCaseImpl()