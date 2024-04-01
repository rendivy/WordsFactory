package ru.yangel.core.validation

class ValidatePasswordUseCase : BaseUseCase<String, ValidationResult> {

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