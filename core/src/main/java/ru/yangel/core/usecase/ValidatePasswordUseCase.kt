package ru.yangel.core.usecase

import ru.yangel.core.validation.BaseUseCase
import ru.yangel.core.validation.ValidationResult

interface ValidatePasswordUseCase : BaseUseCase<String, ValidationResult>