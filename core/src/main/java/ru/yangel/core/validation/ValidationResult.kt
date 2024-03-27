package ru.yangel.core.validation

data class ValidationResult(
    val isSuccessful: Boolean = false,
    val errorType: ErrorType? = null
)