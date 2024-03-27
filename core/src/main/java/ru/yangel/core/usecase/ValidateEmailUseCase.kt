package ru.yangel.core.usecase

import ru.yangel.core.validation.BaseUseCase
import ru.yangel.core.validation.ValidationResult

interface ValidateEmailUseCase : BaseUseCase<String, ValidationResult>